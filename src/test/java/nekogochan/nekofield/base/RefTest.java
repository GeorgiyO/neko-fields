package nekogochan.nekofield.base;

import nekogochan.nekofield.base.Ref;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RefTest {

  @Test
  void constructor() {
    new Ref<>(0);
  }

  @Test
  void set() {
    var field = new Ref<>(0);
    field.set(10);
    assertEquals(10, field.get());
  }

  @Test
  void get() {
    var field = new Ref<>(0);
    assertDoesNotThrow(field::get);
  }
}