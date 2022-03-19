package nekogochan.nekofield.watchable;

import nekogochan.nekofield.base.Ref;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WatchableFieldTest {

  @Test
  void onSet() {
    var field = new WatchableField<>(10);
    var ref = new Ref<>(0);
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
    var field = new WatchableField<>(10);
    var newValueRef = new Ref<>(0);
    var oldValueRef = new Ref<>(0);
    var watcher = field.onSet((_new, _old) -> {
      newValueRef.set(_new);
      oldValueRef.set(_old);
    });

    field.set(20);
    assertEquals(20, field.get());
    assertEquals(20, newValueRef.get());
    assertEquals(10, oldValueRef.get());

    watcher.unwatch();
    field.set(30);
    assertEquals(30, field.get());
    assertEquals(20, newValueRef.get());
    assertEquals(10, oldValueRef.get());
  }
}