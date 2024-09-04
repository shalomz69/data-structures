package com.datastructures.model;

import java.util.Objects;

public class Node<T> {
    public final T data;
    public Node<T> next;
    public Node<T> prev;
    public Node(T data, Node<T> next,  Node<T> prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data)
                && next == node.next
                && prev == node.prev;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(data);
        result = 31 * result + System.identityHashCode(next);
        result = 31 * result + System.identityHashCode(prev);
        return result;
    }
}
