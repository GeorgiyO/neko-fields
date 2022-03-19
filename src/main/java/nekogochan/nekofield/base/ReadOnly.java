package nekogochan.nekofield.base;

import java.util.function.Function;
import java.util.function.Supplier;

public interface ReadOnly<T> extends Supplier<T> {
  default <R> ReadOnly<R> withDecorator(Function<T, R> op) {
    return () -> op.apply(this.get());
  }
}
