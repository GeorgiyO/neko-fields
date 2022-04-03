package nekogochan.field.reactive;

import nekogochan.field.watchable.Atom;
import nekogochan.field.watchable.Watcher;

import java.util.ArrayList;
import java.util.List;

public class Supervisor implements ReactiveComponent {

  private final Runnable action;
  private final List<Watcher> externalAtomsWatchers = new ArrayList<>();

  public Supervisor(Runnable action) {
    this.action = action;
  }

  @Override
  public <T> Atom<T> use(T initValue) {
    var field = new Atom<>(initValue);
    initField(field);
    return field;
  }

  @Override
  public <T> Atom<T> use(Atom<T> field) {
    var watcher = initField(field);
    externalAtomsWatchers.add(watcher);
    return field;
  }

  @Override
  public void free() {
    externalAtomsWatchers.forEach(Watcher::unwatch);
  }

  private Watcher initField(Atom<?> field) {
    return field.onSet(action);
  }
}
