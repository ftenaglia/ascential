package ascential.challenge;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NumberFinderTest {

  NumberFinder numberFinder;
  int valuetoFind = 100;

  @Test
  public void stage1_SequentialProcessing() {
    System.out.println("SequentialNumberFinderImpl");

    numberFinder = new SequentialNumberFinderImpl();
    final long start = System.nanoTime();

    List<CustomNumberEntity> list = numberFinder.readFromFile("input.json");
    boolean result = numberFinder.contains(valuetoFind, list);

    final double totalTime = System.nanoTime() - start;
    System.out.println("  Input size: " + list.size());
    System.out.println("Number found: " + result);
    System.out.format("   Real time: %.2f s\n", totalTime / SECONDS.toNanos(1));
  }

  @Test
  public void stage2_SequentialStreamProcessing() {
    System.out.println("===============");
    System.out.println("StreamNumberFinderImpl");

    numberFinder = new StreamNumberFinderImpl();
    final long start = System.nanoTime();

    List<CustomNumberEntity> list = numberFinder.readFromFile("input.json");
    boolean result = numberFinder.contains(valuetoFind, list);

    final double totalTime = System.nanoTime() - start;
    System.out.println("  Input size: " + list.size());
    System.out.println("Number found: " + result);
    System.out.format("   Real time: %.2f s\n", totalTime / SECONDS.toNanos(1));
  }

  @Test
  public void stage3_ParallelStreamProcessing() {
    System.out.println("===============");
    System.out.println("ParallelStreamNumberFinderImpl");

    numberFinder = new ParallelStreamNumberFinderImpl();
    final long start = System.nanoTime();

    List<CustomNumberEntity> list = numberFinder.readFromFile("input.json");
    boolean result = numberFinder.contains(valuetoFind, list);

    final double totalTime = System.nanoTime() - start;
    System.out.println("  Input size: " + list.size());
    System.out.println("Number found: " + result);
    System.out.format("   Real time: %.2f s\n", totalTime / SECONDS.toNanos(1));
  }
}
