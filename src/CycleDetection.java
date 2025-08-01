public class CycleDetection {
    
    public static void main(String[] args){
        // Find a cycle in an array.  You are given an integer array of
        // size N. Every  element in the array is greater than or equal to 0
        // starting from arr[startIndex], follow each element to the
        // index it points to. Continue to do this until you find a cycle.
        // Return the length of the cycle. If no cycle is found, return -1.
        int[] arr = {1,3,0,4,2};
        int startIndex = 0;
        // Path: 0 -> 1 -> 3 -> 4 -> 2 -> 0 (Cycle length = 5)

        // Use Floyd's Tortoise and Hare:
        // move slow (1 step) and fast (2 steps)
        // to get cycle length, keep one pointer fixed. Move the other
        // until they meet again.
        // if any pointer goes out of bounds, return -1 (no cycle)
        System.out.println(findCycleLength(arr, startIndex));
    }

    public static int findCycleLength(int[] arr, int startIndex) {
        int slow = startIndex;
        int fast = startIndex;

        //detect cycle
        while (true) {
            slow = nextIndex(arr, slow);
            fast = nextIndex(arr, nextIndex(arr, fast));

            if (slow==-1||fast==-1){
                return -1; //no cycle
            }
            if (slow == fast){
                break; // cycle dtected
            }
        }
        // calculate cycle length
        int length = 1;
        int current = nextIndex(arr, slow);
        while (current != slow){
            length++;
            current = nextIndex(arr,current);
        }
        return length;
    }

    private static int nextIndex(int[] arr, int index) {
        if (index < 0 || index >= arr.length){
            return -1; //out of bounds
        }
        return arr[index];
    }
}
