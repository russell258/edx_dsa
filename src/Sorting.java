
import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
 // last-swap optimization: track the last index a swap occurred and shorten the next pass
        int n = arr.length;
        while (n > 1) {
            int lastSwap = 0;
            boolean swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    lastSwap = j + 1; // elements beyond lastSwap are already in place
                }
            }
            if (!swapped) break; // array is sorted
            n = lastSwap;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int n = arr.length;

        // Outer loop iterates through all elements to be placed in the sorted portion
        for (int i = 0; i < n - 1; i++) {
            // Assume the current element is the minimum
            int minIndex = i;

            // Inner loop finds the index of the minimum element in the *remaining* unsorted list (from i+1 to end)
            for (int j = i + 1; j < n; j++) {
                // Use the comparator to compare list.get(j) and list.get(minIndex).
                // If compare returns a value < 0, it means list.get(j) is "smaller"
                // (should come before) list.get(minIndex).
                if (comparator.compare(arr[j], arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the current element (list.get(i))
            if (minIndex != i) {
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }

    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int i=0; i < arr.length; i++){
            T key = arr[i];
            int j = i-1;

            //move elements of arr[0 .. i-1], that are greater than key, to 
            // one position ahead of current position
            while (j>=0 && (comparator.compare(arr[j], key) > 0)){
                arr[j+1] = arr[j];
                j= j -1;
            }
            arr[j+1]= key;

        }
    }
}