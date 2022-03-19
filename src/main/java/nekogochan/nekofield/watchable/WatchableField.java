package nekogochan.nekofield.watchable;

import nekogochan.nekofield.base.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class WatchableField<T> implements Field<T>, Watchable<T> {

  public static int RUN_WATCHERS_ON_CONSTRUCT = 0b1;

  T value;
  List<BiConsumer<T, T>> watchers;

  public WatchableField(T value, List<BiConsumer<T, T>> watchers, int setup) {
    this.watchers = watchers;
    if ((setup & RUN_WATCHERS_ON_CONSTRUCT) == 1) {
      runWatchers(value);
    }
    this.value = value;
  }

  public WatchableField(T value, List<BiConsumer<T, T>> watchers) {
    this(value, watchers, 0);
  }

  public WatchableField(T value) {
    this(value, new ArrayList<>());
  }

  private void runWatchers(T newValue) {
    watchers.forEach(watchers -> watchers.accept(newValue, this.value));
  }

  @Override
  public void set(T value) {
    runWatchers(value);
    this.value = value;
  }

  @Override
  public T get() {
    return value;
  }

  @Override
  public Watcher onSet(Consumer<T> op) {
    BiConsumer<T, T> watcher = (newValue, oldValue) -> op.accept(newValue);
    watchers.add(watcher);
    return () -> watchers.remove(watcher);
  }

  @Override
  public Watcher onSet(BiConsumer<T, T> op) {
    watchers.add(op);
    return () -> watchers.remove(op);
  }
}
