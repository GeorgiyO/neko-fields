package nekogochan.nekofield;

import java.util.function.Consumer;

public interface Field<T> extends ReadOnly<T>, Consumer<T> {
  void set(T value);

  @Override
  default void accept(T t) {
    set(t);
  }
}
