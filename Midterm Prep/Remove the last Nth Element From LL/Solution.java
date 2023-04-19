/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode prevNode = null;
        ListNode slowNode = head;
        ListNode fastNode = head;
        boolean isDone = false;
        int difference = 0;
        while (!isDone) {
            for (int i = 0; i < 2*n && fastNode != null; ++i) {
                fastNode = fastNode.next;
                ++difference;
            }
            // Fastnode reaches at the end of the list.
            if (fastNode == null) {
                // If there are enough elements, set the slowNode to the Nth.
                if (difference >= n) {
                    while (difference != n) {
                        prevNode = slowNode;
                        slowNode = slowNode.next;
                        --difference;
                    }
                    // Check if it is the head
                    if (prevNode == null) head = slowNode.next;
                    else prevNode.next = slowNode.next;
                }
                isDone = true;
            }
            else {
                // Move slow node as n times, decrement the difference
                for (int i = 0; i < n; ++i) {
                    prevNode = slowNode;
                    slowNode = slowNode.next;
                    --difference;
                }
            }
        }
        return head;
    }
}