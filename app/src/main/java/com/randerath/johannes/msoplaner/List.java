package com.randerath.johannes.msoplaner;

/**
 * Created by johannes on 02/02/2017.
 */

class List<T> {

    private class ListNode {

        private T content;
        private ListNode next;

        protected ListNode(T pContent, ListNode pNext) {

            content = pContent;
            next = pNext;

        }

        protected void setContent(T pContent) {
            content = pContent;
        }

        protected T getContent() {
            return content;
        }

        protected void setNext(ListNode n) {
            next = n;
        }

        protected ListNode getNext() {
            return next;
        }

        protected boolean hasNext() {
            return next != null;
        }

    }

    private ListNode first;
    private ListNode current;
    private ListNode last;

    public List(){
        first = null;
        current = null;
        last = null;
    }

    public void append(T pContent) {

        if(pContent != null) {
            ListNode n = new ListNode(pContent, null);
            last.setNext(n);
            last = n;
            if(isEmpty()) first = n;
        }

    }

    public void concat(List<T> second) {
        if(!second.isEmpty()) {
            second.toFirst();
            append(second.getContent());
        }
    }

    public T getContent() {
        if(hasAccess()) {
            return current.getContent();
        }else {
            return null;
        }
    }

    public boolean hasAccess() {
        return current != null;
    }

    public void insert(T pContent) {
        if(pContent != null) {
            if (hasAccess()) {
                ListNode n = new ListNode(pContent, current);
                if (first == current) first = n;
                else {
                    getPrevious().setNext(n);
                }
            }else if(isEmpty()){
                append(pContent);
            }
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void next() {
        if(hasAccess()) current = current.getNext();
        else this.toFirst();
    }

    public void previous() {
        current = getPrevious();
    }

    public void remove() {
        if(hasAccess()) {
            if(first == current) first = current.getNext();
            else {
                if(last == current) last = getPrevious();
                else getPrevious().setNext(current.getNext());
            }
        }
    }

    public void setContent(T pContent) {

        if(hasAccess() && pContent != null) {
            current.setContent(pContent);
        }

    }

    public void toFirst() {
        current = first;
    }

    public void toLast() {
        current = last;
    }

    private ListNode getPrevious() {

        if(hasAccess() && current != first) {
            ListNode n = first;
            while(n.getNext() != current) n = n.getNext();
            return n;
        }
        return null;
    }

    public int getSize() {

        int i = 0;
        this.toFirst();
        while (hasAccess()) {
            i++;
            this.next();
        }
        return i;
    }

}
