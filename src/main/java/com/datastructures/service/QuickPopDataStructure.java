package com.datastructures.service;


import com.datastructures.model.Node;
import org.springframework.stereotype.Service;

@Service
public class QuickPopDataStructure<T extends Comparable<T>> {
    private final Node<T> headStub = new Node<>(null, null, null);

    public synchronized void push(T data) {
        if(data == null){
            return;
        }
        if (headStub.next == null) {
            headStub.next = new Node<>(data, null, headStub);
            return;
        }
        Node<T> temp = headStub.next;
        while (temp.next != null && data.compareTo(temp.data) < 0) {
            temp = temp.next;
        }
        if (data.compareTo(temp.data) < 0) {
            temp.next = new Node<>(data, null, temp);
        } else {
            Node<T> prev = temp.prev;
            prev.next = new Node<>(data, prev.next, prev);
            temp.prev = prev.next;
        }
    }

    public synchronized T pop() {
        if (headStub.next == null) {
            return null;
        }
        Node<T> toPop = headStub.next;
        headStub.next = toPop.next;
        if(toPop.next !=null) {
            toPop.next.prev = headStub;
        }
        return toPop.data;
    }
}
