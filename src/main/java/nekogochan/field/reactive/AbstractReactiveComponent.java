package nekogochan.field.reactive;

import nekogochan.field.watchable.Atom;
import nekogochan.field.watchable.Watcher;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes"})
public abstract class AbstractReactiveComponent implements ReactiveComponent {

  private final List<Watcher> externalFieldsListeners = new ArrayList<>();
  private boolean needUpdate = false;
  private boolean inAct = false;

  @Override
  public <T> Atom<T> use(T initValue) {
    var field = new Atom<>(initValue);
    initField(field);
    return field;
  }

  @Override
  public <T> Atom<T> use(Atom<T> field) {
    var watcher = initField(field);
    externalFieldsListeners.add(watcher);
    return field;
  }

  protected abstract void abstractAct();

  public void act() {
    beforeAct();
    abstractAct();
    afterAct();
  }

  public void free() {
    externalFieldsListeners.forEach(Watcher::unwatch);
  }

  private void beforeAct() {
    needUpdate = false;
    inAct = true;
  }

  private void afterAct() {
    if (needUpdate) {
      this.act();
    }
    inAct = false;
  }

  private Watcher initField(Atom<?> field) {
    return field.onSet(() -> {
      if (this.inAct) {
        needUpdate = true;
      } else {
        act();
      }
    });
  }
}
