package TenInter;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现 LRUCache 类：
 *    以及如下接口
 *   LRUCache(int capacity) // 以正整数作为容量 capacity 初始化 LRU 缓存
 *     int get(int key) //如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 *     void put(int key, int value) //如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 *     当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * */
class LRUCache1{

    int capacity;
    Map<Integer,Integer> LRU;

    //获取缓存
    public LRUCache1(int capacity) {
        this.capacity = capacity;
        LRU = new HashMap<>(capacity);
    }

    //获取关键字
    public int getKey(int key){
        if(!LRU.containsKey(key)){
            return -1;
        }
        Integer value = LRU.remove(key);
        LRU.put(key, value);
        return value;
    }

    //放入数据
    public void put(int key,int value){
        if (LRU.containsKey(key)) {
            LRU.remove(key);
            LRU.put(key, value);
            return;
        }
        LRU.put(key, value);
        // 超出capacity，删除最久没用的,利用迭代器删除第一个
        if (LRU.size() > capacity) {
            LRU.remove(LRU.entrySet().iterator().next().getKey());
        }
    }

}
public class Interview {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            ListNode superNode = new ListNode(-1);
            superNode.next = head;
            ListNode tmp = superNode;
            while(tmp.next != null){
                if(tmp.next.val == val){
                    tmp.next = tmp.next.next;
                    break;
                }
                tmp = tmp.next;
            }
            return superNode.next;
        }
    }

    private static ListNode remove(ListNode head,int val){
        ListNode myhead = new ListNode(-1);
        ListNode curr = head;
        ListNode next = null;
        ListNode tmpe = myhead;
        while (curr != null){
            if(curr.next.val == val){
                //当前节点的两位节点赋值给当前节点
                curr = curr.next.next;
                //节点赋值
                curr.next.val = curr.next.next.val;
                break;
            }

        }
        return curr;
    }

    private static ListNode insert(ListNode head,int val){
        ListNode curr = null;
        curr = head.next;
        curr.val = val;
        head.next.val = val;
        return curr;
    }
    public static void main(String[] args) {
//        String aa = "9999999";
//        char[] rr = {'0','1','2','3','4','5','6','7','8','9'};
//        char[] cc = aa.toCharArray();
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i = 0; i < rr.length; i++) {
//            if(cc[i] == rr[i]){
//
//            }
//        }



    }


}


