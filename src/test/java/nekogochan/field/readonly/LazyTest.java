package nekogochan.field.readonly;

import nekogochan.functional.ref.IntRef;
import nekogochan.functional.ref.Ref;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class LazyTest {

  @Test
  void lazyTest() {
    var initTimes = new IntRef(0);
    var lazyField = Lazy.of(() -> {
      initTimes.increment();
      return "some value";
    });

    assertEquals(0, initTimes.get());

    lazyField.get();
    assertEquals(1, initTimes.get());

    lazyField.get();
    assertEquals(1, initTimes.get());
  }

  @RepeatedTest(100)
  void lazyTest_multithreading() {
    var initTimes = new IntRef(0);
    var lazyField = Lazy.of(() -> {
      initTimes.increment();
      return 0;
    });
    var threads = Stream.<Runnable>generate(() -> lazyField::get)
                        .map(Thread::new)
                        .limit(10)
                        .collect(toList());

    threads.forEach(Thread::start);
    threads.forEach(thread -> {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    assertEquals(1, initTimes.get());
  }
}