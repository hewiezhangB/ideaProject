package TenInter;

import java.util.LinkedHashMap;
import java.util.Map;

public class TencentInterview {
    /**
     * 第一道题，面试官要求：实现String.valueOf(),和Integer。parseInt()底层实现
     * 要求:此题为简单题目：7min完成
     * 此题未做出
     * */
    public static void main(String[] args) {
        String aa = "1995889661";
        System.out.println(parseInt(aa,10));
    }

    /**
     * @param radix 进制数
     * */
    public static int parseInt(String s, int radix) throws NumberFormatException {
        //字符串不能为空
        if (s == null) {
            throw new NumberFormatException("转换字符串为空！");
        }
        //至少为二进制
        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + "至少为二进制");
        }
        //不能大于36进制
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + "不能大于36进制");
        }

        int result = 0;
        //正负标记，默认为负
        boolean flag = false;
        int i = 0, len = s.length();
        int maxValue = -Integer.MAX_VALUE;
        //最小范围
        int limitValue;
        int digit;
        if (len > 0) {
            char firstChar = s.charAt(0);
            //0字符的ASCII是48
            //只有带符号位的会判断，其他默认正数
            if (firstChar < '0') {
                //负数
                if (firstChar == '-') {
                    flag = true;
                    maxValue = Integer.MIN_VALUE;
                } else if (firstChar != '+'){
                    throw new RuntimeException("字符串输入异常");
                }
                if (len == 1){
                    throw new RuntimeException("字符串输入异常：无符号位字符");
                }
                i++;
            }
            limitValue = maxValue / radix;
            while (i < len) {
                //拿到字符，转换为ASCII数字并按进制转为数字
                digit = Character.digit(s.charAt(i++),radix);
                if (digit < 0) {
                    throw new RuntimeException("字符串输入异常：此时不应该带有符号位");
                }
                //最小限制不能为负数
                if (result < limitValue) {
                    throw new RuntimeException("字符串输入异常：此时不应该带有符号位");
                }
                //由高位到地位，需要进制，向后移动乘以进制
                result *= radix;
                //对应ASCII码数字最小验证
                if (result < maxValue + digit) {
                    throw new RuntimeException("字符串输入异常");
                }
                //反向移动，字符从后向前进位（负数）
                result -= digit;
            }
        } else {
            throw new RuntimeException("字符串输入异常");
        }
        //负数和正数校验，返回正数/负数
        return flag ? result : -result;
    }

    /**
     * 第二道题，面试官要求：实现链表的删除与增加元素
     * 要求:此题为简单题目：10min完成
     * 面试时此题已做出
     * */
    /**
     * 删除列表的节点（切记，和删除链表中的节点不同，首次编码时候写成了第二种，面试官说给你一次机会纠正）
     * 入参要自己想，面试官不会给出
     * */
    private static ListNode removeNode(ListNode head,int val){
        //要删除的头节点后的节点
        if(head.val == val){
            return head.next;
        }
        ListNode pre = head;
        ListNode curr = head.next;
        //移动链表
        while (curr != null && curr.val != val){
            pre = curr;
            curr = curr.next;
        }
        //现在执行的是删除操作
        if(curr != null){
            pre.next = curr.next;
        }
        return head;
    }

    /**
     * 易于理解，但损耗性能
     * */
    private static ListNode remove(ListNode head,int val){
        ListNode myNode = new ListNode(-1);
        myNode.next = head;
        ListNode temp = myNode;
        //遍历链表
        while(temp.next != null){
            if(temp.next.val == val){
                temp.next = temp.next.next;
                break;
            }
            //移动链表
            temp = temp.next;
        }
        return myNode.next;
    }

    /**
     * 面试官要求：头结点后插入
     * */
    private static void insert(ListNode head,int val){
        ListNode myNode = new ListNode(val);
        myNode.next = head.next;
        head.next = myNode;
    }

    /**
     * 第三题：LRU缓存，
     * 实现 LRUCache 类：以及如下接口
     * LRUCache(int capacity) // 以正整数作为容量 capacity 初始化 LRU 缓存
     * int get(int key) //如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
     * void put(int key, int value) //如果关键字已经存在，则变更其数据值；
     * 如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
     * 已作出，第一次见此题编码慢，后来发现是力扣上原题，他复制过来的
     * 面试官要求：20min
     * */

}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}

class LRUCache {
    int capacity;
    Map<Integer,Integer> Cache;
    public LRUCache(int capacity) {
        //以正整数作为容量 capacity 初始化 LRU 缓存
        this.capacity = capacity;
        Cache = new LinkedHashMap<>(capacity);
    }

    public int get(int key) {
        // 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
        if(!Cache.containsKey(key)){
            return -1;
        }
        Integer value = Cache.remove(key);
        Cache.put(key,value);
        return value;
    }

    public void put(int key, int value) {
        //如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
        // 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
        if(Cache.containsKey(key)){
            Cache.remove(key);
            Cache.put(key,value);
            return;
        }
        Cache.put(key,value);
        if(Cache.size() > capacity){
            Cache.remove(Cache.entrySet().iterator().next().getKey());
        }
    }
}

/**
 * 以上就是我腾讯面试的试题，答对了后两道，未通过
 * 说说面试感言吧：个人感觉这个面试官数据结构挺好，就是要做的项目他十分不熟悉，通过我对他的提问，发现他应该属于组长一类
 * 平时不怎么开发系统，负责安排任务和面试员工，所有有时间看数据结构
 * 但是有一点不太好，他的态度很差，问他idea编程的话网页弹出警告改怎么办？他不知道
 * 而且在我编程的过程中，有遇到难点卡住，要求他提示的时候，他态度就很差，就说这不难呀，这都不会，而且他固定与他手中的数据结构很古板
 * 所以我腾讯问卷调查没怎么评价
 *
 * */

