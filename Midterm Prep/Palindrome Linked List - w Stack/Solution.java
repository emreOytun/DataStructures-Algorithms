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
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode slowNode = head;
        ListNode fastNode = head;
        while (fastNode != null && fastNode.next != null) {
            stack.push(slowNode.val);
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }
        if (fastNode != null) {
            slowNode = slowNode.next;
        }
        while (slowNode != null) {
            if (stack.isEmpty() || slowNode.val != stack.pop()) return false;
            slowNode = slowNode.next;
        }
        return true;
    }
}