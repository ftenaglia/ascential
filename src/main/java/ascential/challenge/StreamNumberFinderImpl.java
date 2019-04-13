package ascential.challenge;

import java.util.List;

public class StreamNumberFinderImpl extends AbstractNumberFinder {

  private final FastestComparator comparator = new FastestComparator();

  public boolean contains(final int valueToFind, final List<CustomNumberEntity> list) {

    return list.stream()
        .filter(e -> e.getNumber() != null && NumberUtil.isInt(e.getNumber()))
        .anyMatch(e -> comparator.compare(valueToFind, e) == 0);
  }
}
