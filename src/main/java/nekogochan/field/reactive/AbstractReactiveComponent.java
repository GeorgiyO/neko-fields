package nekogochan.field.reactive;

import nekogochan.field.watchable.Atom;
import nekogochan.field.watchable.Watcher;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"rawtypes"})
public abstract class AbstractReactiveComponent implements ReactiveComponent {

  List<Atom> fields = new ArrayList<>();
  List<Watcher> externalFieldsListeners = new ArrayList<>();
  boolean needUpdate = false;
  boolean inAct = false;

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

  @Override
  public void act() {
    beforeAct();
    abstractAct();
    afterAct();
  }

  protected void free() {
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
    fields.add(field);
    return field.onSet(() -> {
      if (this.inAct) {
        needUpdate = true;
      } else {
        act();
      }
    });
  }
}
