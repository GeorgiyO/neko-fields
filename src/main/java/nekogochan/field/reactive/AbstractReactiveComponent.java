package nekogochan.field.reactive;

import nekogochan.field.watchable.WatchableField;
import nekogochan.field.watchable.Watcher;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@SuppressWarnings({"rawtypes"})
public abstract class AbstractReactiveComponent implements ReactiveComponent {

  List<WatchableField> fields = new ArrayList<>();
  List<Watcher> externalFieldsListeners = new ArrayList<>();
  boolean firstCall = true;
  boolean needUpdate = false;
  boolean inAct = false;

  @Override
  public <T> WatchableField<T> use(T initValue) {
    var field = new WatchableField<>(initValue);
    initField(field);
    return field;
  }

  @Override
  public <T> WatchableField<T> use(WatchableField<T> field) {
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

  protected void clear() {
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

  private Watcher initField(WatchableField<?> field) {
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
