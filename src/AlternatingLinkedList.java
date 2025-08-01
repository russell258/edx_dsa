public class AlternatingLinkedList {
    
    public static void main(String[] args) {
        // Given a linkedlist 1-> 2 -> 3 -> 4 -> 5
        // retuen new linkedlist with every alternate element, odd indexed
        // element if starting as 0-indexed, being added at the last
        // new linkedlist will look like 1->3 -> 5 -> 2 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Head of original list:");
        printList(head);
        System.out.println("Head of rearranged list:");
        printList(rearrange(head));

    }

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public static void printList(ListNode head) {
        while (head!=null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode rearrange(ListNode head) {
        if (head == null || head.next == null) {
            return head; // No rearrangement needed for empty or single node list
        }
        ListNode evenHead = head;
        ListNode oddHead = head.next;
        ListNode even = evenHead;
        ListNode odd = oddHead;

        while (odd!=null && odd.next !=null) {
            even.next = odd.next; 
            even = even.next;

            odd.next = even.next;
            odd = odd.next;
        }
        even.next = oddHead; // append odd list at the end
        return evenHead; // return the head of the rearranged list
    }

}
