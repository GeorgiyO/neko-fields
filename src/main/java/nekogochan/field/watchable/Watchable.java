package nekogochan.field.watchable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Watchable<T> {
  default Watcher onSet(Runnable op) {
    return onSet((newValue, oldValue) -> op.run());
  }

  default Watcher onSet(Consumer<T> op) {
    return onSet((newValue, oldValue) -> op.accept(newValue));
  }

  Watcher onSet(BiConsumer<T, T> op);
}
