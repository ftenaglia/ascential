package ascential.challenge;

import java.util.List;

public class SequentialNumberFinderImpl extends AbstractNumberFinder {

  FastestComparator comparator = new FastestComparator();

  public boolean contains(int valueToFind, List<CustomNumberEntity> list) {
    for (CustomNumberEntity e : list) {
      if (e.getNumber() != null && NumberUtil.isInt(e.getNumber())) {
        if (comparator.compare(valueToFind, e) == 0) {
          return true;
        }
      }
    }
    return false;
  }
}
