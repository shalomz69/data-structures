package com.datastructures.service;

import com.datastructures.model.Node;
import org.springframework.stereotype.Service;

@Service
public class QuickPushDataStructure<T extends Comparable<T>> {
    private final Node<T> headStub = new Node<>(null, null, null);

    public synchronized void push(T data) {
        if(data == null){
            return;
        }
        Node<T> next = headStub.next;
        headStub.next = new Node<>(data, headStub.next, headStub);
        if (next != null) {
            next.prev = headStub.next;
        }
    }

    public synchronized T pop() {
        if (headStub.next == null) {
            return null;
        }
        Node<T> temp = headStub.next;
        Node<T> max = temp;
        while (temp != null) {
            if (max.data.compareTo(temp.data) < 0) {
                max = temp;
            }
            temp = temp.next;
        }

        Node<T> prev = max.prev;
        prev.next = max.next;

        if (max.next != null) {
            max.next.prev = prev;
        }
        return max.data;
    }
}
