package nekogochan.nekofield.base;

import nekogochan.nekofield.Field;

public class Ref<T> implements Field<T> {

  T value;

  public Ref(T value) {
    this.value = value;
  }

  public Ref() {
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
