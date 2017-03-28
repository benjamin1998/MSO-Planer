package com.randerath.johannes.msoplaner;

class Stack<T> {

    private class StackNode  {

        private T content;
        private StackNode next;

        public StackNode(T pContent, StackNode pNext){

            content = pContent;
            next = pNext;

        }

        public void setNext(StackNode pNext) {
            next = pNext;
        }

        public StackNode getNext() {
            return next;
        }

        public void setContent(T pContent) {
            content = pContent;
        }

        public T getContent() {
            return content;
        }

        public boolean hasNext() {
            return next != null;
        }


    }

    private StackNode top;

    public Stack() {
        top = null;
    }

    public boolean isEmpty() {

        return top == null;

    }

    public void push(T pContent) {

        if(pContent != null) {
            StackNode n = new StackNode(pContent, top);
            top = n;
        }

    }

    public void pop() {

        if(!isEmpty()) {
            top = top.getNext();
        }

    }

    public T top() {

        if(!isEmpty()) {
            return top.getContent();
        }else {
            return null;
        }

    }

    public int getSize() {

        int size = 0;

        if(!isEmpty()) {

            size++;

            for(StackNode n = top; n.hasNext(); n = n.getNext()) {
                size ++;
            }

        }

        return size;


    }

}
