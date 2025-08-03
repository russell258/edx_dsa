public class OverlappingRectangle {
    public static void main(String[] args) {
        // Given two rectangles, find if the given two rectangles overlap or not.
        // Note that a rectangle can be represented by two coordinates, top left and bottom right. So mainly we are given following four coordinates. 
        // l1: Top Left coordinate of first rectangle. 
        // r1: Bottom Right coordinate of first rectangle. 
        // l2: Top Left coordinate of second rectangle. 
        // r2: Bottom Right coordinate of second rectangle.

        int[] l1 = {0, 10};
        int[] r1 = {10, 0};
        int[] l2 = {-10, 5};
        int[] r2 = {-1, 0};
        
        System.out.println("Do the rectangles overlap? " + overlap(l1, r1, l2, r2));

    }

    public static boolean overlap(int[] l1, int[] r1, int[] l2, int[] r2) {
        if (l1[0] >= r2[0] || l2[0] >= r1[0]) {
            return false;
        }

        if (l1[1] <= r2[1] || l2[1] <= r1[1]) {
            return false;
        }

        return true; // Rectangles overlap
    }
}
