package com.laioffer.Algorithm.linkedlist;
import java.util.*;

public class MyHashMap {
    static class Node<K,V> {
        final K key;
        V value;
        Node<K,V> next;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
        public void setValue(V value) {
            this.value = value;
        }
    }
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private Node<K,V>[] array;
    private int size;
    private float loadFactor;
    private int SCALE_FACTOR = 2;

    public MyHashMap() {
        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
    }
    public MyHashMap(int cap, float loadFactor) {
        if (cap<=0) {
            throw new IllegalArgumentException("cap can not be negative");
        }
        this.array = (Node<K,V>[]) (new Node[cap]);
        this.size = 0;
        this.loadFactor = loadFactor;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size()==0;
    }
    public void clear() {
        Arrays.fill(this.array,null);
        size=0;
    }
    private int hash(K key) {
        if (key==null) {return 0;}
        return key.hashCode() & 0x7FFFFFFF;
    }
    private int getIndex(K key) {
        return hash(key)%array.length;
    }
    private boolean equalsKey(K k1, K k2) {
        if (k1==k2) {return true;}
        if (k1==null || k2==null) {return false;}
        return k1.equals(k2);
    }
    private boolean equalsValue(V v1, V v2) {
        if (v1==v2) {return true;}
        if (v1==null || v2==null) {return false;}
        return v1.equals(v2);
    }
    private boolean containsKey(K key) {
        int index = getIndex(key);
        Node<K,V> node = array[index];
        if (node==null) {return false;}
        while (node!=null) {
            if (equalsKey(key,node.getKey())) {
                return true;
            }
            node=node.next;
        }
        return false;
    }
    private boolean containsValue(V value) {
        if (isEmpty()) {return false;}
        for (Node<K,V> node : array) {
            while (node!=null) {
                if (equalsValue(value, node.getValue())) {
                    return true;
                }
                node=node.next;
            }
        }
        return false;
    }
    public V get(K key) {
        int index = getIndex(key);
        Node<K,V> node = array[index];
        while (node!=null) {
            if (equalsKey(key,node.key)) {
                return node.value;
            }
            node=node.next;
        }
        return null;
    }
    public V put(K key, V value) {
        int index = getIndex(key);
        Node<K,V> node = array[index];
        while (node!=null) {
            if (equalsValue(value,node.value)) {
                return node.value;
            }
            node=node.next;
        }
        Node<K,V> newHead = new Node(key,value);
        newHead.next=array[index];
        array[index]=newHead;
        size++;
        if (needRehashing()) {
            rehashing();
        }
        return null;
    }
    private boolean needRehashing() {
        float ratio = (size+0.0f)/array.length;
        return ratio>=loadFactor;
    }
    private void rehashing() {
        Node<K,V>[] oldArray = array;
        array = (Node<K,V>[]) new Node[array.length*SCALE_FACTOR];
        for (Node<K,V> node : oldArray) {
            while (node!=null) {
                Node<K,V> next = node.next;
                int index = getIndex(node.key);
                node.next=array[index];
                array[index]=node;
                node=next;
            }
        }
    }
    public V remove(K key, V value) {
        int index = getIndex(key);
        Node<K,V> node = array[index];
        Node<K,V> prev = null;
        while (node!=null) {
            if (equalsKey(key,node.key)) {
                if (prev!=null) {
                    prev.next=node.next;
                } else {
                    array[index]=node.next;
                }
                size--;
                return node.value;
            }
            prev = node;
            node = node.next;
        }
        return null;

        //        int index = getIndex(key);
//        Node<K,V> node = array[index];
//        Node<K,V> prev = null;
//        while (node!=null) {
//            if (equalsKey(node.key,key)) {
//                if (prev!=null) {
//                    prev.next=node.next;
//                } else {
//                    array[index] = node.next;
//                }
//                size--;
//                return node.value;
//            }
//            prev=node;
//            node=node.next;
//        }
//        return null;

    }

    //    public static class Node<K,V> {
//        final K key;
//        V value;
//        Node<K,V> next;
//        Node(K key, V value) {
//            this.key=key;
//            this.value=value;
//        }
//        public K getKey() {
//            return key;
//        }
//        public V getValue() {
//            return value;
//        }
//        public void setValue(V value) {
//            this.value=value;
//        }
//    }
//    private static final int DEFAULT_CAPACITY=16;
//    private static final float DEFAULT_LOAD_FACTOR=0.75f;
//    private Node<K,V>[] array;
//    private int size; // how many key-value pairs are stored
//    private float loadFactor;
//    private int SCALE_FACTOR=2;
//
//    public MyHashMap() {
//        this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
//    }
//    public MyHashMap(int cap, float loadFactor) {
//        if (cap<=0) {
//            throw new IllegalArgumentException("cap can not be negative");
//        }
//        this.array=(Node<K,V>[]) (new Node[cap]);
//        this.size=0;
//        this.loadFactor=loadFactor;
//    }
//    public int size() {
//        return size;
//    }
//    public boolean isEmpty() {
//        return size==0;
//    }
//    public void clear() {
//        Arrays.fill(array,null);
//        size=0;
//    }
//    private int hash(K key) {
//        if (key==null) {return 0;}
//        return key.hashCode() & 0x7FFFFFFF;
//        // java's % return remainder rather than modulus
//    }
//    private int getIndex(K key) {
//        return hash(key) % array.length;
//    }
//    private boolean equalsValue(V v1, V v2) {
//        return v1==v2 || v1!=null && v1.equals(v2);
//    }
//    private boolean equalsKey(K k1, K k2)
//    {
//        return k1==k2 || k1!=null && k1.equals(k2);
//    }
//    public boolean containsValue(V value) {
//        if (isEmpty()) {return false;}
//        for (Node<K,V> node : array) {
//            while (node!=null) {
//                if (equalsValue(node.value,value)) {
//                    return true;
//                }
//                node=node.next;
//            }
//        }
//        return false;
//    }
//    public boolean containsKey(K key) {
//        int index=getIndex(key);
//        Node<K,V> node = array[index];
//        while (node!=null) {
//            if (equalsKey(node.key,key)) {
//                return true;
//            }
//            node=node.next;
//        }
//        return false;
//    }
//    public V get(K key) {
//        int index=getIndex(key);
//        Node<K,V> node = array[index];
//        while (node!=null) {
//            if (equalsKey(node.key,key)) {
//                return node.value;
//            }
//            node=node.next;
//        }
//        return null;
//    }
//    public V put(K key, V value) {
//        int index=getIndex(key);
//        Node<K,V> head = array[index];
//        Node<K,V> node = head;
//        while (node!=null) {
//            if (equalsKey(node.key,key)) {
//                V result = node.value;
//                node.value=value;
//                return result;
//            }
//            node=node.next;
//        }
//        Node<K,V> newNode = new Node(key,value);
//        newNode.next=head;
//        array[index]=newNode;
//        size++;
//        if (needRehashing()) {
//            rehashing();
//        }
//        return null;
//    }
//    private boolean needRehashing() {
//        float ratio = (size+0.0f)/array.length;
//        return ratio>=loadFactor;
//    }
//    private void rehashing() {
//        NOde<K,V>[] oldArray = array;
//        array = (NOde<K,V>[]) (new Node[array.length*SCALE_FACTOR]);
//        for (Node<K,V> node : oldArray) {
//            while (node!=null) {
//                Node<K,V> next = node.next;
//                int index=getIndex(node.key);
//                node.next=array[index];
//                array[index]=node;
//                node = next;
//            }
//        }
//    }
//    public V remove(K key) {
//        int index = getIndex(key);
//        Node<K,V> node = array[index];
//        Node<K,V> prev = null;
//        while (node!=null) {
//            if (equalsKey(node.key,key)) {
//                if (prev!=null) {
//                    prev.next=node.next;
//                } else {
//                    array[index] = node.next;
//                }
//                size--;
//                return node.value;
//            }
//            prev=node;
//            node=node.next;
//        }
//        return null;
//    }
}
