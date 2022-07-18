package com.wusy.wusylibrary.util;

/**
 * 算法工具类
 */
public class AlgorithmUtil {
    /**
     * 二分法
     * 查找指定元素
     * 左右闭区间写法[x,y]
     * 题目（Leetcode704）：
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
     * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     *
     * @param nums 数组集合，为有序数组，同时数组中无重复元素。这是二分法的使用前提
     * @param target 查找的目标元素
     * @return
     */
    public int dichotomy(int[] nums, int target) {
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){//说明target还在右边
                left=mid+1;
            }else{//说明target还在左边
                right=mid-1;
            }
        }
        return -1;
    }

    /**
     * 题目（LeetCode27）：
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 双指正解法---快慢指针法
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slowIndex=0;
        for (int fastIndex=0;fastIndex<nums.length;fastIndex++){
            if(nums[fastIndex]!=val){//判断不相等时，将快指针的值赋给慢指针
                nums[slowIndex++]=nums[fastIndex];
            }
        }
        return slowIndex;
    }
}
