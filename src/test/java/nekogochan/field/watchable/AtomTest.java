package nekogochan.field.watchable;

import nekogochan.functional.ref.IntRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AtomTest {

  @Test
  void onSet() {
    var field = new Atom<>(10);
    var ref = new IntRef(0);
    var watcher = field.onSet(ref::set);

    field.set(20);

    assertEquals(20, field.get());
    assertEquals(20, ref.get());

    watcher.unwatch();
    field.set(30);

    assertEquals(30, field.get());
    assertEquals(20, ref.get());
  }

  @Test
  void onSet_2() {
    var field = new Atom<>(1);
    var newValueRef = new IntRef(0);
    var oldValueRef = new IntRef(0);
    var watcher = field.onSet((_new, _old) -> {
      newValueRef.set(_new);
      oldValueRef.set(_old);
    });

    field.set(2);
    assertEquals(2, field.get());
    assertEquals(2, newValueRef.get());
    assertEquals(1, oldValueRef.get());

    watcher.unwatch();
    field.set(3);
    assertEquals(3, field.get());
    assertEquals(2, newValueRef.get());
    assertEquals(1, oldValueRef.get());
  }
}