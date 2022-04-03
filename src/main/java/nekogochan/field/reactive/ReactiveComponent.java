package nekogochan.field.reactive;

import nekogochan.field.watchable.Atom;

public interface ReactiveComponent {
  <T> Atom<T> use(T initValue);
  <T> Atom<T> use(Atom<T> field);

  void act();
}
