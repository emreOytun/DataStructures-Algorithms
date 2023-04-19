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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode head = null;
        if (list1.val < list2.val) {
            head = list1;
            list1 = list1.next;
        }
        else {
            head = list2;
            list2 = list2.next;
        }

        ListNode curNode = head;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curNode.next = list1;
                curNode = list1;
                list1 = list1.next;
            }
            else {
                curNode.next = list2;
                curNode = list2;
                list2 = list2.next;
            }
        }

        while (list1 != null) {
            curNode.next = list1;
            curNode = list1;
            list1 = list1.next;
        }

        while (list2 != null) {
            curNode.next = list2;
            curNode = list2;
            list2 = list2.next;
        }
        return head;
    }
}