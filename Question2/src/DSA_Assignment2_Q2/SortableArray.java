package DSA_Assignment2_Q2;

/**
 * Assignment Questions:
 * <p>
 * 1. Is quick sort the best way of finding median? Why? (3%)
 * <p>
 * 2. What is another good way of finding median? Please provide your
 * explanation. (3%)
 * <p>
 * Answers:
 * 1. I think quick sort is good but not the best way of finding the median.
 * This is because it is fast as it's average and best time complexity is
 * O(nlogn) but at rare cases, worst at O(n^2). Also, the median may already
 * be found but the method carries on recursively calling itself until the whole
 * array is sorted.
 * <p>
 * 2. Another good way of finding median is using merge sort. This is because
 * the time complexity in all three cases (average, best, worst) is O(nlogn),
 * unlike quick sort which is O(n^2) for worst case.
 * This class takes in a generic comparable array and contains a quick sort
 * method for sorting the array.
 *
 * @author eirik
 * @param <E> Generic Comparable object
 */
public class SortableArray<E extends Comparable> {

    public E[] array;

    public void setArray(E[] array) {
        this.array = array;
    }

    /**
     * This calls a private method that is a quick sort implementation using
     * recursion to sort the array.
     */
    public void quickSort() {
        quickSort(0, array.length - 1);
    }

    /**
     * This method is q quick sort implementation that uses recursion to sort
     * the array. This method works by having two pointers on the array
     * (fromHead and fromTail) and a pivot which is the tail.
     * <p>
     * The two pointers iterate up and down respectively until the fromHead
     * pointer reaches an object that is bigger than the pivot object
     * and the fromTail pointer reaches an object smaller than the pivot.
     * <p>
     * The two pointers than swap their contents. This carries on until the two
     * pointers cross each other.
     * At this point, the method call itself (recursion) and carries out the
     * same process again until the array is sorted.
     *
     * @param head This is the index of the first element of the array.
     * @param tail This is the index of the last element of the array.
     */
    private void quickSort(int head, int tail) {
        if (head < tail) {
            E pivot = array[tail];
            int fromHead = head; // pointer to head
            int fromTail = tail - 1; // pointer to tail (not including pivot)

            // keep looping until the two pointers cross
            do {
                // search up from the head until found object in array more than the pivot
                while (fromHead <= tail && array[fromHead].compareTo(pivot) < 0) {
                    fromHead++;
                }

                // search down from the tail until found object in array less than the pivot
                while (fromTail >= head && array[fromTail].compareTo(pivot) >= 0) {
                    fromTail--;
                }

                // swap the contents of the two pointers to swap side of pivot
                if (fromHead < fromTail) {
                    E temp = array[fromHead];
                    array[fromHead] = array[fromTail];
                    array[fromTail] = temp;
                }
            } while (!(fromTail < fromHead));

            // swap the index of the pivot
            int pivotIndex = fromHead;
            E temp = array[pivotIndex];
            array[pivotIndex] = array[tail];
            array[tail] = temp;

            // split up and sort the array from below and above the pivot.
            quickSort(head, pivotIndex - 1);
            quickSort(pivotIndex + 1, tail);
        }
    }
}
