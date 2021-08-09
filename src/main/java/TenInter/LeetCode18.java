package TenInter;

//class ListNode{
//    int val;
//    ListNode next;
//    public ListNode(int val) {
//        this.val = val;
//    }
//}
public class LeetCode18 {
    public static void main(String[] args) {

    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head.val == val){
            return head.next;
        }
        ListNode pre = head,curr = head.next;
        while (curr != null && curr.val == val){
            pre = curr;
            curr = curr.next;
        }
        if(curr != null){
            pre.next = curr.next;
        }
        return head;
    }
}
