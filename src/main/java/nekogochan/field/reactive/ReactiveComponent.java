package nekogochan.field.reactive;

import nekogochan.field.watchable.WatchableField;

public interface ReactiveComponent {
  <T> WatchableField<T> use(T initValue);
  <T> WatchableField<T> use(WatchableField<T> field);

  void act();
}
