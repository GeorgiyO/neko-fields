package nekogochan.nekofield.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseReadOnlyFieldTest {

  @SuppressWarnings("SimplifiableAssertion")
  @Test
  void get() {
    var _1 = new BaseReadOnlyField<>();
    assertEquals(null, _1.get());

    var _2 = new BaseReadOnlyField<>(10);
    assertEquals(10, _2.get());

    var _3 = new BaseReadOnlyField<>(() -> 10);
    assertEquals(10, _3.get());
  }
}