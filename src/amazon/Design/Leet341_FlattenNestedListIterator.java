package amazon.Design;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Leet341_FlattenNestedListIterator {
      public interface NestedInteger {
 
              // @return true if this NestedInteger holds a single integer, rather than a nested list.
              public boolean isInteger();
 
              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger();
 
              // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return empty list if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
  }
    public class NestedIterator implements Iterator<Integer> {
        List<NestedInteger> nestedList;
        List<Integer> integerList;
        int index;
        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            integerList = flattenNestedInteger(nestedList);
            this.index = 0;
        }

        private List<Integer> flattenNestedInteger(List<NestedInteger> nestedList) {
            List<Integer> res = new ArrayList<>();
            for (NestedInteger n : nestedList) {
                if (n.isInteger()) {
                    res.add(n.getInteger());
                } else {
                    res.addAll(flattenNestedInteger(n.getList()));
                }
            }
            return res;
        }

        @Override
        public Integer next() {
            if (hasNext()) {
                return integerList.get(index++);
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            if (index > integerList.size() - 1) {
                return false;
            }
            return true;
        }
    }
}
