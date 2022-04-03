package nekogochan.field.watchable;

import nekogochan.field.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class Atom<T> implements Field<T>, Watchable<T> {

  public static int RUN_WATCHERS_ON_CONSTRUCT = 0b1;

  protected T value;
  protected List<BiConsumer<T, T>> watchers;

  public Atom(T value, List<BiConsumer<T, T>> watchers, int setup) {
    this.watchers = watchers;
    if ((setup & RUN_WATCHERS_ON_CONSTRUCT) == 1) {
      runWatchers(value);
    }
    this.value = value;
  }

  public Atom(T value, List<BiConsumer<T, T>> watchers) {
    this(value, watchers, 0);
  }

  public Atom(T value) {
    this(value, new ArrayList<>());
  }

  private void runWatchers(T newValue) {
    watchers.forEach(it -> it.accept(newValue, this.value));
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
  public Watcher onSet(BiConsumer<T, T> op) {
    watchers.add(op);
    return () -> watchers.remove(op);
  }
}
