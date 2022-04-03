package nekogochan.field.reactive;

import nekogochan.field.compose.Composite;
import nekogochan.field.watchable.Atom;
import nekogochan.functional.ref.IntRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SupervisorTest {
  @Test
  void simpleInternalTest() {
    var updates = new IntRef(0);
    var sv = new Supervisor(updates::increment);
    var state = sv.use(0);

    assertEquals(0, updates.get());

    state.set(0);
    assertEquals(1, updates.get());

    state.set(0);
    assertEquals(2, updates.get());
  }

  @Test
  void simpleExternalTest() {
    var updates = new IntRef(0);
    var state = new Atom<>(0);
    var sv = new Supervisor(updates::increment);
    var svState = sv.use(state);

    assertEquals(svState, state);
    assertEquals(0, updates.get());

    state.set(0);
    assertEquals(1, updates.get());

    state.set(0);
    assertEquals(2, updates.get());
  }

  @Test
  void supervisor_willTrigger_onExternalStateChange_evenIfCantBeUsed() {
    var state = new Atom<>(0);
    {
      var sv = new Supervisor(() -> { throw new RuntimeException(); });
      sv.use(state);
    }
    assertThrows(RuntimeException.class,
                 () -> state.set(0));
  }

  @Test
  void supervisor_wouldntBeTrigger_onExternalStateChange_ifHasBeenFreed() {
    var state = new Atom<>(0);
    {
      var sv = new Supervisor(() -> { throw new RuntimeException(); });
      sv.use(state);
      sv.free();
    }
    assertDoesNotThrow(() -> state.set(0));
  }
}
