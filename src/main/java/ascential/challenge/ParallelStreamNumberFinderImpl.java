package ascential.challenge;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreamNumberFinderImpl extends AbstractNumberFinder {

  private static final int THREADS_PER_CORE = 2;
  private final FastestComparator comparator = new FastestComparator();
  private final int threads;

  public ParallelStreamNumberFinderImpl() {
    this.threads = Runtime.getRuntime().availableProcessors() * THREADS_PER_CORE;
  }

  public boolean contains(final int valueToFind, final List<CustomNumberEntity> list) {

    ForkJoinPool customThreadPool = new ForkJoinPool(threads);
    boolean result = false;
    try {
      result =
          customThreadPool
              .submit(
                  () ->
                      list.parallelStream()
                          .filter(e -> e.getNumber() != null && NumberUtil.isInt(e.getNumber()))
                          .anyMatch(e -> comparator.compare(valueToFind, e) == 0))
              .get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    return result;
  }
}
