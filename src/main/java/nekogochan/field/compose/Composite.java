package nekogochan.field.compose;

import nekogochan.field.watchable.Atom;
import nekogochan.functional.function.Fn1;
import nekogochan.functional.function.Fn10;
import nekogochan.functional.function.Fn2;
import nekogochan.functional.function.Fn3;
import nekogochan.functional.function.Fn4;
import nekogochan.functional.function.Fn5;
import nekogochan.functional.function.Fn6;
import nekogochan.functional.function.Fn7;
import nekogochan.functional.function.Fn8;
import nekogochan.functional.function.Fn9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("rawtypes")
public class Composite<R> extends Atom<R> implements Supplier<R> {

  boolean needUpdate = true;
  Supplier<R> valueProvider;
  private final List<Composite> dependent = new ArrayList<>();

  public <T1> Composite(Atom<T1> t1, Fn1<T1, R> mapper) {
    super(null);
    List.of(t1).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get());
  }

  public <T1, T2> Composite(Atom<T1> t1, Atom<T2> t2, Fn2<T1, T2, R> mapper) {
    super(null);
    List.of(t1, t2).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get());
  }

  public <T1, T2, T3> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Fn3<T1, T2, T3, R> mapper) {
    super(null);
    List.of(t1, t2, t3).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get());
  }

  public <T1, T2, T3, T4> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Fn4<T1, T2, T3, T4, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get());
  }

  public <T1, T2, T3, T4, T5> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Atom<T5> t5, Fn5<T1, T2, T3, T4, T5, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4, t5).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get(), t5.get());
  }

  public <T1, T2, T3, T4, T5, T6> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Atom<T5> t5, Atom<T6> t6, Fn6<T1, T2, T3, T4, T5, T6, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4, t5, t6).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get(), t5.get(), t6.get());
  }

  public <T1, T2, T3, T4, T5, T6, T7> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Atom<T5> t5, Atom<T6> t6, Atom<T7> t7, Fn7<T1, T2, T3, T4, T5, T6, T7, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4, t5, t6, t7).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get(), t5.get(), t6.get(), t7.get());
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Atom<T5> t5, Atom<T6> t6, Atom<T7> t7, Atom<T8> t8, Fn8<T1, T2, T3, T4, T5, T6, T7, T8, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4, t5, t6, t7, t8).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get(), t5.get(), t6.get(), t7.get(), t8.get());
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Atom<T5> t5, Atom<T6> t6, Atom<T7> t7, Atom<T8> t8, Atom<T9> t9, Fn9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4, t5, t6, t7, t8, t9).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get(), t5.get(), t6.get(), t7.get(), t8.get(), t9.get());
  }

  public <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> Composite(Atom<T1> t1, Atom<T2> t2, Atom<T3> t3, Atom<T4> t4, Atom<T5> t5, Atom<T6> t6, Atom<T7> t7, Atom<T8> t8, Atom<T9> t9, Atom<T10> t10, Fn10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> mapper) {
    super(null);
    List.of(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10).forEach(this::init);
    valueProvider = () -> mapper.apply(t1.get(), t2.get(), t3.get(), t4.get(), t5.get(), t6.get(), t7.get(), t8.get(), t9.get(), t10.get());
  }

  private void init(Atom<?> field) {
    if (field instanceof Composite) {
      ((Composite<?>) field).dependent.add(this);
    } else {
      field.onSet($ -> this.fire_needUpdate());
    }
  }

  protected void fire_needUpdate() {
    needUpdate = true;
    dependent.forEach(Composite::fire_needUpdate);
  }

  @Override
  public R get() {
    if (needUpdate) {
      super.set(valueProvider.get());
      needUpdate = false;
    }
    return super.get();
  }
}