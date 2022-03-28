package nekogochan.field;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Field<T> extends Supplier<T>, Consumer<T> {
  void set(T value);

  @Override
  default void accept(T t) {
    set(t);
  }
}
