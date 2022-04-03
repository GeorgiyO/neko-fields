package nekogochan.field.compose;

import nekogochan.field.watchable.Atom;
import nekogochan.functional.ref.IntRef;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositeTest {

  @Test
  void oneCommonFieldDependencyTest() {
    var dependency = new Atom<>("string");
    var composite = new Composite<>(dependency, String::length);
    var changes = new IntRef(0);
    composite.onSet(changes::increment);

    assertEquals(6, composite.get());
    assertEquals(1, changes.get()); // composite will change inner value on ::get call

    composite.get();
    assertEquals(1, changes.get()); // composite didn't change until dependency isn't changed

    dependency.set("cat");
    assertEquals(1, changes.get()); // composite didn't change until ::get is called

    assertEquals(3, composite.get());
    assertEquals(2, changes.get()); // composite changed after ::get call
  }

  @Test
  void twoLevelCompositeTest() {
    var dependency = new Atom<>("Kitty");
    var firstComposite = new Composite<>(dependency, String::length);
    var secondComposite = new Composite<>(firstComposite, i -> i + 10);
    var changes = new IntRef(0);
    secondComposite.onSet(changes::increment);
    firstComposite.onSet(changes::increment);

    assertEquals(15, secondComposite.get()); // when get second composite, we will get 2 changes for both composites
    assertEquals(2, changes.get());

    dependency.set("");

    assertEquals(10, secondComposite.get());
    assertEquals(4, changes.get());

    assertEquals(0, firstComposite.get()); // when get first composite after first composite, we will get 0 extra changes
    assertEquals(4, changes.get());
  }

  @Test
  void twoDependenciesCompositeTest() {
    var firstName = new Atom<>("Ivan");
    var secondName = new Atom<>("Ivanov");
    var fullName = new Composite<>(firstName, secondName, (_1, _2) -> _1 + " " + _2);
    var changes = new IntRef(0);
    fullName.onSet(changes::increment);

    assertEquals("Ivan Ivanov", fullName.get());
    assertEquals(1, changes.get());

    firstName.set("Petr");
    secondName.set("Petrov");

    assertEquals("Petr Petrov", fullName.get());
    assertEquals(2, changes.get());

    shit();
  }

  static void shit() {

  }
}