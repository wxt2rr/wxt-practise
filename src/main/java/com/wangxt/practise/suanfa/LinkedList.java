package com.wangxt.practise.suanfa;

import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

public class LinkedList {

    @Data
    static class Node{
        private int value;
        private Node next;
        public Node(int value){
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(5);
        node.next.next = new Node(6);
        //node.next.next.next = node.next;

        //print(node);
       // Node node1 = revertList(node);
        //print(node1);

       // boolean b = hadLoop(node);
     //   System.out.println(b);


        Node node2 = new Node(2);
        node2.next = new Node(3);
        node2.next.next = new Node(7);

        Node merge = merge(node, node2);
        print(merge);

        t(node);
        System.out.println(node);

        tt();
    }

    private static void print(Node node1){
        while (node1 != null){
            System.out.println(node1.value);
            node1 = node1.next;
        }
    }

    /**
     *反转链表
     * 1 -> 2 -> 3 -> null
     *
     * null -> 3 -> 2 -> 1
     */
    private static Node revertList(Node head){
        if(head == null){
            return null;
        }

        if(head.next == null){
            return head;
        }

        Node prev = null;
        while(head != null){
            Node next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    /**
     * 判断链表中是否有环
     * 说白了就是看链表中
     * @param head
     * @return
     */
    private static boolean hadLoop(Node head){
        Set<Node> set = new HashSet<>();
        while (head != null){
            if(set.contains(head)){
                return true;
            }

            set.add(head);

            head = head.next;
        }

        return false;
    }

    /**
     * 合并两个有序链表
     * @param one
     * @param two
     * @return
     */
    private static Node merge(Node one, Node two){
        if(one == null && two == null){
            return null;
        }

        Node result = new Node(-1);
        Node point = result;
        while (one != null && two != null){
            if(one.value < two.value){
                point.next = one;
                one = one.next;
            }else {
                point.next = two;
                two = two.next;
            }

            point = point.next;
        }

        if(one != null){
            point.next = one;
        }

        if(two != null){
            point.next = two;
        }


        // 把初始指针去掉
        return result.next;
    }

    private static void t(Node n){
        Node p = n;
        n.setValue(1000);

        int value = p.getValue();
        System.out.println(value);
    }



    public static void tt(){
        Node a = new Node(100);

        Node b = new Node(1000);

        a.setNext(b);

        b = new Node(0);
        System.out.println();
    }
}
