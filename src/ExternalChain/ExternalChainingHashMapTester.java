package ExternalChain;

public class ExternalChainingHashMapTester {

    public static void main(String[] args) {
        System.out.println("=== Test 1: put(-23, -23) ===");
        testPutNeg23();

        System.out.println("\n=== Test 2: put(1, 1) (resize behavior) ===");
        testPut1();

        System.out.println("\n=== Test 3: remove(key) demonstration ===");
        testRemoveDemo();
    }

    private static void testPutNeg23() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        ExternalChainingMapEntry<Integer, Integer>[] table = map.getTable();

        // Before : indices -> entries as in failure report
        // index 1: (-1,-1), 6: (-6,-6), 8: (-8,-8), 12: (-25,-25)
        table[1] = new ExternalChainingMapEntry<>(-1, -1, null);
        table[6] = new ExternalChainingMapEntry<>(-6, -6, null);
        table[8] = new ExternalChainingMapEntry<>(-8, -8, null);
        table[12] = new ExternalChainingMapEntry<>(-25, -25, null);
        // set size to match number of entries (put uses size for load factor)
        setSize(map, 4);

        System.out.println("Before:");
        System.out.println(formatTable(table));

        try {
            map.put(-23, -23);
        } catch (Exception e) {
            System.out.println("put threw: " + e);
            e.printStackTrace();
        }

        System.out.println("After put(-23,-23):");
        System.out.println(formatTable(map.getTable()));
        System.out.println("size = " + map.size());
    }

    private static void testPut1() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        ExternalChainingMapEntry<Integer, Integer>[] table = map.getTable();

        // Before from failure: populate buckets
        // index 0: (13,13), 1:(53,53), 2:(15,15), 3:(3,3), 4:(17,17),
        // 5:(5,5), 6:(19,19), 10:(218,218)
        table[0] = new ExternalChainingMapEntry<>(13, 13, null);
        table[1] = new ExternalChainingMapEntry<>(53, 53, null);
        table[2] = new ExternalChainingMapEntry<>(15, 15, null);
        table[3] = new ExternalChainingMapEntry<>(3, 3, null);
        table[4] = new ExternalChainingMapEntry<>(17, 17, null);
        table[5] = new ExternalChainingMapEntry<>(5, 5, null);
        table[6] = new ExternalChainingMapEntry<>(19, 19, null);
        table[10] = new ExternalChainingMapEntry<>(218, 218, null);
        setSize(map, 8);

        System.out.println("Before:");
        System.out.println(formatTable(table));
        try {
            map.put(1, 1);
        } catch (Exception e) {
            System.out.println("put threw: " + e);
            e.printStackTrace();
        }
        System.out.println("After put(1,1):");
        System.out.println(formatTable(map.getTable()));
        System.out.println("size = " + map.size());
    }

    private static void testRemoveDemo() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        ExternalChainingMapEntry<Integer, Integer>[] table = map.getTable();
        // put a few values via direct chaining to demonstrate remove
        table[2] = new ExternalChainingMapEntry<>(2, 2, null);
        table[2].setNext(new ExternalChainingMapEntry<>(15, 15, null));
        table[5] = new ExternalChainingMapEntry<>(5, 5, null);
        setSize(map, 3);

        System.out.println("Before remove:");
        System.out.println(formatTable(table));
        try {
            Integer removed = map.remove(15);
            System.out.println("removed: " + removed);
        } catch (Exception e) {
            System.out.println("remove threw: " + e);
            e.printStackTrace();
        }
        System.out.println("After remove:");
        System.out.println(formatTable(map.getTable()));
        System.out.println("size = " + map.size());
    }

    // Helpers

    private static void setSize(ExternalChainingHashMap<?, ?> map, int s) {
        try {
            java.lang.reflect.Field f = ExternalChainingHashMap.class.getDeclaredField("size");
            f.setAccessible(true);
            f.setInt(map, s);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String formatTable(ExternalChainingMapEntry<?, ?>[] table) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < table.length; i++) {
            sb.append("  ").append(i).append(": ");
            ExternalChainingMapEntry<?, ?> cur = table[i];
            if (cur == null) {
                sb.append("null");
            } else {
                while (cur != null) {
                    sb.append("(").append(cur.getKey()).append(", ").append(cur.getValue()).append(")");
                    cur = cur.getNext();
                    if (cur != null) {
                        sb.append(" -> ");
                    }
                }
            }
            sb.append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
}