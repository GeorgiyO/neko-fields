package nekogochan.field.reactive;

import nekogochan.field.watchable.Atom;
import nekogochan.functional.ref.IntRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractReactiveComponentTest {

  @Test
  void simpleInternalStateTest() {
    var updates = new IntRef(0);
    var component = new AbstractReactiveComponent() {
      final Atom<Integer> state = use(0);

      @Override
      protected void abstractAct() {
        updates.increment();
      }
    };
    var state = component.state;

    assertEquals(0, updates.get());

    state.set(0);
    assertEquals(1, updates.get());

    state.set(0);
    assertEquals(2, updates.get());
  }

  @Test
  void simpleExternalStateTest() {
    var updates = new IntRef(0);
    var state = new Atom<>(0);
    var component = new AbstractReactiveComponent() {
      final Atom<Integer> __state = use(state);

      @Override
      protected void abstractAct() {
        updates.increment();
      }
    };

    assertEquals(state, component.__state);
    assertEquals(0, updates.get());

    state.set(0);
    assertEquals(1, updates.get());

    state.set(0);
    assertEquals(2, updates.get());
  }

  @Test
  void component_thatUsesMultipleState_shouldUpdateOneTime_ifUpdateBothInAct() {
    var updates = new IntRef(0);
    var limit = 5;
    var component = new AbstractReactiveComponent() {
      final Atom<Integer> state_1 = use(0), state_2 = use(0);
      int i = 0;

      @Override
      protected void abstractAct() {
        if (i < limit) {
          state_1.set(0);
          state_2.set(0);
          i++;
        }
        updates.increment();
      }
    };
    component.act(); // trigger act

    assertEquals(6, updates.get()); // 1 external + 5 internal updates
  }
}
