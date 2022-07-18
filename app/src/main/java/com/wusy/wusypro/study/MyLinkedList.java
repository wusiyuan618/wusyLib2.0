package com.wusy.wusypro.study;

import java.util.List;

public class MyLinkedList {
    /**
     * 存储链表的个数
     */
    int size;

    /**
     * 虚拟头节点
     */
    ListNode head;


    public MyLinkedList() {
        size=0;
        head=new ListNode(0);
    }

    public int get(int index) {
        if(index<0||index>=size) {
            return -1;
        }
        ListNode curNode=head;
        for (int i=0;i<=index;i++){//因为有虚拟节点所以获取的index的下一个
            curNode=curNode.next;
        }
        return curNode.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0,val);
    }

    public void addAtTail(int val) {
        addAtIndex(size,val);
    }

    public void addAtIndex(int index, int val) {
        if(index>size) return;
        if(index<0) index=0;
        ListNode pre=head;
        for (int i=0;i<index;i++){
            pre=pre.next;
        }
        ListNode toAdd=new ListNode(val);
        toAdd.next=pre.next;
        pre.next=toAdd;
        size++;
    }

    public void deleteAtIndex(int index) {
        if(index>=size||index<0)return;
        ListNode pre=head;
        for (int i=0;i<index;i++){
            pre=pre.next;
        }
        pre.next=pre.next.next;
        size--;
    }
}
