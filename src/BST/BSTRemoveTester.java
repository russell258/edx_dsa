package BST;

public class BSTRemoveTester {

    public static void main(String[] args) {
        runTest("Remove 1 from [1,0,2]", new int[] {1,0,2}, 1);
        runTest("Remove 3 from [3,0,4,1,2]", new int[] {3,0,4,1,2}, 3);
        runTest("Remove 2 from [2,1,3,0]", new int[] {2,1,3,0}, 2);
        runTest("Remove 4 from [4,0,5,1,3,2]", new int[] {4,0,5,1,3,2}, 4);
        runTest("Remove 3 from [1,0,5,3,2,4,6,7]", new int[] {1,0,5,3,2,4,6,7}, 3);
        runTest("Remove 5 from [1,0,5,3,2,4,6,7]", new int[] {1,0,5,3,2,4,6,7}, 5);
        runTest("Remove 4 from [1,0,4,3,2,5,6]", new int[] {1,0,4,3,2,5,6}, 4);
        runTest("Remove 5 from [1,0,5,2,4,3,6,7]", new int[] {1,0,5,2,4,3,6,7}, 5);
    }

    private static void runTest(String name, int[] inserts, int removeVal) {
        System.out.println("=== " + name + " ===");
        BST<Integer> tree = new BST<>();
        for (int v : inserts) {
            tree.add(v);
        }
        System.out.println("Initial tree:");
        printTree(tree.getRoot(), 0);
        try {
            Integer removed = tree.remove(removeVal);
            System.out.println("\nRemoved: " + removed);
        } catch (Exception e) {
            System.out.println("\nremove() threw: " + e);
        }
        System.out.println("\nAfter remove:");
        printTree(tree.getRoot(), 0);
        System.out.println("size = " + tree.size());
        System.out.println();
    }

    // prints tree sideways: right subtree first, then node, then left subtree
    private static void printTree(BSTNode<Integer> node, int depth) {
        if (node == null) {
            return;
        }
        printTree(node.getRight(), depth + 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("    ");
        }
        sb.append(node.getData());
        System.out.println(sb.toString());
        printTree(node.getLeft(), depth + 1);
    }
}