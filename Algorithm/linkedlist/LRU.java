package com.laioffer.Algorithm.linkedlist;

public class LRU { // 205
//    static class Node<K,V> {
//        Node<K,V> prev,next;
//        K key;
//        V value;
//        Node(K key, V value) {
//            this.key=key;
//            this.value=value;
//        }
//        void update(K key, V value) {
//            this.key=key;
//            this.value=value;
//        }
//    }
//
//    private final int limit;
//    private Node<K,V> head,tail;
//    private Map<K,Node<K,V>> map;
//
//    // limit is the max capacity of the cache
//    LRU(int limit) {
//        this.limit=limit;
//        this.map=new HashMap<K,Node<K,V>>();
//    }
//
//    public void set(K key, V value) {
//        Node<K,V> node = null;
//        if (map.containsKey(key)) {
//            node=map.get(key);
//            node.value=value;
//            remove(node);
//        } else if (map.size()<limit) {
//            node=new Node<K,V>(key,value);
//        } else { // hit the limit, remove the tail node
//            node=tail;
//            remove(node);
//            node.update(key,value);
//        }
//        append(node);
//    }
//
//    public V get(K key) {
//        Node<K,V> node = map.get(key);
//        if (node==null) {return null;}
//        remove(node);
//        append(node);
//        return node.value;
//    }
//
//    private Node<K,V> remove (Node<K,V> node) {
//        // remove from map
//        map.remove(node.key);
//        // update linkedlist pointer
//        if (node.prev!=null) {
//            node.prev.next=node.next;
//        }
//        if (node.next!=null) {
//            node.next.prev=node.prev;
//        }
//        if (node==head) {
//            head=head.next;
//        }
//        if (node==tail) {
//            tail=tail.prev;
//        }
//        // disconnect node
//        node.next=node.prev=null;
//        return node;
//    }
//
//    private Node<K,V> append(Node<K,V> node) {
//        // update map
//        map.put(node.key,node);
//        // updated linkedlist pointers
//        if (head==null) {
//            head=tail=node;
//        } else { // put node as new head
//            node.next=head;
//            head.prev=node;
//            head=node;
//        }
//        return node;
//    }
    public static void main(String[] args) {
        LRU solution = new LRU();
    }
}
