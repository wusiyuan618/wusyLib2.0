package com.wusy.wusypro.study;

import com.wusy.wusylibrary.util.AlgorithmUtil;

import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MathTest {
    public static void main(String[] args) {
//        ListNode node5=new ListNode(5,null);
//        ListNode node4=new ListNode(4,node5);
//        ListNode node3=new ListNode(3,node4);
//        ListNode node2=new ListNode(2,node3);
//        ListNode node1 = new ListNode(1, null);
//        int i=12%10;
//        System.out.println(i);

    }

    public String reverseStr(String s, int k) {
        for(int i=0;i<s.toCharArray().length;i++){

        }
        return "";
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++){
            if(nums[i]>0){
                return result;
            }
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int left=i+1;
            int right=nums.length-1;
            while (left<right){
                int sum=nums[i]+nums[left]+nums[right];
                if(sum>0){
                    right--;
                }else if(sum<0){
                    left++;
                }else{
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map=new HashMap<>();
        for(char c:magazine.toCharArray()){
            if(map.containsKey(c)){
                int count=map.get(c)+1;
                map.put(c,count);
            }else{
                map.put(c,1);
            }
        }
        for (char c:ransomNote.toCharArray()){
            if(map.containsKey(c)){
                int count=map.get(c)-1;
                if(count<0){
                    return false;
                }else{
                    map.put(c,count);
                }
            }else{
                return false;
            }
        }
        return true;
    }
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
        int result=0;
        for (int i:nums1){
            for (int j:nums2){
                int sum=i+j;
                if(map.containsKey(sum)){
                    int count=map.get(sum)+1;
                    map.put(sum,count);
                }else{
                    map.put(sum,1);
                }
            }
        }
        for (int i:nums3){
            for (int j:nums4){
                if(map.containsKey(0-i-j)){
                    result+=map.get(0-i-j);
                }
            }
        }
        return result;
    }
    public int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            int temp =target-nums[i];
            if(map.containsKey(temp)){
                result[0]=i;
                result[1]=map.get(temp);
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA=headA;
        ListNode curB=headB;
        int countA=1;
        int countB=1;
        while(curA!=null){
            curA=curA.next;
            countA++;
        }
        while (curB!=null){
            curB=curB.next;
            countB++;
        }
        int count=Math.abs(countA-countB);
        if(countA-countB >=0){//A比B长
            while (count-->0){
                headA=headA.next;
            }
        }else{
            while (count-->0){
                headB=headB.next;
            }
        }
        while (headA!=null){
            if(headA==headB){
                return headA;
            }
            headA=headA.next;
            headB=headB.next;
        }
        return null;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode slowNode = dummy;
        ListNode fastNode = dummy;
        while (n-- > 0) {
            fastNode = fastNode.next;
        }
        ListNode prev = null;
        while (fastNode != null) {
            prev = slowNode;
            slowNode = slowNode.next;
            fastNode = fastNode.next;
        }
        prev.next = slowNode.next;
        return dummy.next;
    }


    public ListNode swapPairs(ListNode head) {
        return swapPairsD(head);
    }

    public ListNode swapPairsD(ListNode head) {
        // base case 退出提交
        if (head == null || head.next == null) return head;
        // 获取当前节点的下一个节点
        ListNode next = head.next;
        // 进行递归
        ListNode newNode = swapPairs(next.next);
        // 这里进行交换
        next.next = head;
        head.next = newNode;

        return next;
    }

    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode temp = cur.next.next;
            pre.next = cur.next;
            pre.next.next = cur;
            cur.next = temp;
            pre = cur;
            cur = temp;
        }
        return dummy.next;
    }

    private ListNode reverseList(ListNode pre, ListNode cur) {
        if (cur == null) return pre;
        ListNode temp = cur.next;
        cur.next = pre;
        return reverseList(cur, temp);
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public int minSubArrayLen(int target, int[] nums) {
        int leftIndex = 0;
        int rightIndex = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (; rightIndex < nums.length; rightIndex++) {
            sum += nums[rightIndex];
            while (sum >= target) {
                result = Math.min(result, rightIndex - leftIndex + 1);
                sum -= nums[leftIndex++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int startX = 0, startY = 0;//定制圈循环时候的起始位置。
        int offset = 1;//每一圈循环，控制一个边界长度。绘制下一个圈时，因为左右边界均复制，所以需要+2
        int loop = n / 2;//需要循环的次数
        int count = 1;
        while (loop > 0) {
            int x = startX;
            int y = startY;
            for (; y < startY + n - offset; ++y) {
                result[startX][y] = count++;
            }
            for (; x < startX + n - offset; ++x) {
                result[x][y] = count++;
            }
            for (; y > startY; y--) {
                result[x][y] = count++;
            }
            for (; x > startX; x--) {
                result[x][y] = count++;
            }

            loop--;
            startX += 1;
            startY += 1;
            offset += 2;
        }
        if (n % 2 == 1) {//n为奇数，中间项要单独赋值
            result[n / 2][n / 2] = count;
        }
        return result;
    }
}
