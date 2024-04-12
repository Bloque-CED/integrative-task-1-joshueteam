package ownStructures;
import java.util.ArrayList;

public class Stack<T> implements IStack<T> {
    private AuxiliaryLinkedList<T> stackList;

    public Stack() {
        stackList = new AuxiliaryLinkedList<>();
    }

    @Override
    public void push(T item) {
        stackList.addFirst(item);
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            return stackList.removeFirst();
        }
        return null;
    }

    @Override
    public T peek() {
        if (!isEmpty()) {
            return stackList.getFirst();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return stackList.size() == 0;
    }

    @Override
    public int size() {
        return stackList.size();
    }

}
