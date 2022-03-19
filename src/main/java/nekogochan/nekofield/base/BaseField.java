package nekogochan.nekofield.base;

import nekogochan.nekofield.Field;

public class BaseField<T> implements Field<T> {

  T value;

  public BaseField(T value) {
    this.value = value;
  }

  public BaseField() {
    this(null);
  }

  @Override
  public void set(T value) {
    this.value = value;
  }

  @Override
  public T get() {
    return this.value;
  }
}
