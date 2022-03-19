package nekogochan.nekofield.watchable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public interface Watchable<T> {
  Watcher onSet(Consumer<T> op);
  Watcher onSet(BiConsumer<T, T> op);
}
