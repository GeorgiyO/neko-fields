package nekogochan.nekofield.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseFieldTest {

  @Test
  void constructor() {
    new BaseField<>(0);
    new BaseField<>();
  }

  @Test
  void set() {
    var field = new BaseField<>(0);
    field.set(10);
    assertEquals(10, field.get());
  }

  @Test
  void get() {
    var field = new BaseField<>(0);
    assertDoesNotThrow(field::get);
  }
}