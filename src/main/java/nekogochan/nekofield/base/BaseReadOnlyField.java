package nekogochan.nekofield.base;

import nekogochan.nekofield.ReadOnly;

import java.util.function.Supplier;

public class BaseReadOnlyField<T> implements ReadOnly<T> {

  Supplier<T> value;

  public BaseReadOnlyField(Supplier<T> value) {
    this.value = value;
  }

  public BaseReadOnlyField(T value) {
    this(() -> value);
  }

  public BaseReadOnlyField() {
    this(() -> null);
  }

  @Override
  public T get() {
    return value.get();
  }
}
