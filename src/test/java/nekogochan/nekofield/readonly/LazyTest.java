package nekogochan.nekofield.readonly;

import nekogochan.nekofield.field.Ref;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LazyTest {

  @Test
  void _lazy() {
    var initTimes = new Ref<>(0);
    var lazyField = Lazy.of(() -> {
      initTimes.set(initTimes.get() + 1);
      return "some value";
    });
    assertEquals(0, initTimes.get());
    assertEquals("some value", lazyField.get());
    assertEquals(1, initTimes.get());
    lazyField.get();
    assertEquals(1, initTimes.get());
  }

  @RepeatedTest(100)
  void _multithreading_lazy() {
    var initTimes = new Ref<>(0);
    var lazyField = Lazy.of(() -> {
      initTimes.set(initTimes.get() + 1);
      return 0;
    });
    var threads = Stream.<Runnable>generate(() -> lazyField::get)
                        .map(Thread::new)
                        .limit(10)
                        .toList();
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