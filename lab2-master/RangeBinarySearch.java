
import java.util.Comparator;

public class RangeBinarySearch {

    // Returns the index of the *first* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there is no matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static <T> int firstIndexOf(T[] a, T key, Comparator<T> comparator) {
        // TODO
        // null?
        if (a == null)
            throw new NullPointerException("array of keys cannot be null");
        if (key == null)
            throw new NullPointerException("key cannot be null");
        if (comparator == null)
            throw new NullPointerException("comparator cannot be null");
        if (a.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = a.length - 1;
        if (comparator.compare(a[0], key) == 0) {
            return 0;
        }
        while (lo <= hi) {
            int middle = (lo + hi) / 2;
            // go left if smaller

            if (comparator.compare(key, a[middle]) > 0) {
                lo = middle + 1;
            } else if (comparator.compare(key, a[middle]) < 0) {
                hi = middle - 1;// changed lo to hi
            } else if (comparator.compare(a[middle - 1], a[middle]) == 0) {
                hi = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
        // throw new UnsupportedOperationException();
    }

    // Returns the index of the *last* element in `a` that equals the search key,
    // according to the given comparator, or -1 if there are is matching element.
    // Precondition: `a` is sorted according to the given comparator.
    // Complexity: O(log N) comparisons where N is the length of `a`
    public static <T> int lastIndexOf(T[] a, T key, Comparator<T> comparator) {
        // TODO
        if (a == null)
            throw new NullPointerException("array of keys cannot be null");
        if (key == null)
            throw new NullPointerException("key cannot be null");
        if (comparator == null)
            throw new NullPointerException("comparator cannot be null");
        if (a.length == 0) {
            return -1;
        }
        int lo = 0;
        int hi = a.length - 1;
        if (comparator.compare(a[hi], key) == 0) {
            return hi;
        }

        int result = -1;

        while (lo <= hi) {
            int middle = (lo + hi) / 2;
            // go left if smaller

            if (comparator.compare(key, a[middle]) > 0) {
                lo = middle + 1;
            } else if (comparator.compare(key, a[middle]) < 0) {
                hi = middle - 1;// changed lo to hi
            } else if (comparator.compare(a[middle + 1], a[middle]) == 0) {
                lo = middle + 1;
            } else{
                return middle;}
        }

        return result;

        // throw new UnsupportedOperationException();
    }

}
