package com.leetcode;

/**
 * Created with IntelliJ IDEA.
 * Author: CR
 * Date: 2017/8/7
 * Time: 23:30
 * GitHub: https://github.com/cryeshiren
 * Description: Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLists {
    /**
     * 首先，进行边界条件判断，如果任一一个表是空表，就返回另外一个表。
     * 然后，对于新表选取第一个node，选择两个表表头最小的那个作为新表表头，指针后挪。
     * 然后同时遍历两个表，进行拼接。
     * 因为表已经是sorted了，最后把没有遍历完的表接在新表后面。
     * 由于新表也会指针挪动，这里同时需要fakehead帮助记录原始表头。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode l3;
        if (l1.val < l2.val) {
            l3 = l1;
            l1 = l1.next;
        } else {
            l3 = l2;
            l2 = l2.next;
        }

        ListNode fakehead = new ListNode(-1);
        fakehead.next = l3;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                l3.next = l1;
                l3 = l3.next;
                l1 = l1.next;
            } else {
                l3.next = l2;
                l3 = l3.next;
                l2 = l2.next;
            }
        }

        if (l1 != null)
            l3.next = l1;
        if (l2 != null)
            l3.next = l2;
        return fakehead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        System.out.println(mergeTwoLists(l1, l2));
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
