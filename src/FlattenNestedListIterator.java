import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */


// NestedInteger is of 2 types (Integer, List)
// Process the list "nestedList" in constructor
// Whenever we got integer, add it to the list, else process it again through recursion
// TC: constructor - O(n), next - O(1) , hasNext() - O(1)
public class FlattenNestedListIterator implements Iterator<Integer> {
    List<Integer> list;
    int i;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.list = new ArrayList<>();
        dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedList) {
        for(int i=0;i<nestedList.size();i++) {
            if(nestedList.get(i).isInteger()) list.add(nestedList.get(i).getInteger());
            else dfs(nestedList.get(i).getList());
        }
    }

    @Override
    public Integer next() {
        int element = list.get(i);
        i++;
        return element;
    }

    @Override
    public boolean hasNext() {
        return i!= list.size();
    }
}



// TC: O(1) constructor, hasNext(), next()
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> st;
    NestedInteger nextEl;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.st = new Stack<>();
        st.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextEl.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!st.isEmpty()) {
            if(!st.peek().hasNext()) {
                st.pop();
            } else {
                nextEl = st.peek().next();
                if(nextEl.isInteger()) return true;
                else st.push(nextEl.getList().iterator());
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
