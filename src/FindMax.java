import java.util.HashMap;

public class FindMax {

    /**
     * This method should return the largest number in the passed in array.
     * 
     * Constraints:
     * 1 <= nums.length <= 100
     * -10000 <= nums[i] <= 10000
     * 
     * @param nums An array containing integers.
     * @return The largest number in nums.
     */
    public static int findMax(int[] nums) {
        // Write your solution here.
        int max = -10000;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    HashMap<Integer,  String> map = new HashMap<>();
}