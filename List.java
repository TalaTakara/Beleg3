package List;

public class List<T> implements Listable<T> {

    Node head;

    private class Node{
        T data;
        Node next;
    }

    public void add(T data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        if ( head == null){
            head = newNode;
        }else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }

    }

    public void clear(T data) {
        head = null;
    }

    public boolean isEmpty(T data) {

        return (head == null);
    }
//
//	public void insertAt(int index, T data) {
//
//		Node newNode = new Node();
//		newNode.data = data;
//		newNode.next = null;
//
//		Node temp = head;
//		for ( int i = 0 ; i < index -1; i++){
//			temp = temp.next;
//		}
//		newNode.next = temp.next;
//		temp.next = newNode;
//
//	}

    public void remove(int index) {
        Node temp = head;
        for ( int i = 0 ; i < index; i++){
            temp = temp.next;
        }
        if (temp.next != null){
            temp.next = temp.next.next;
        }
    }

    public int getsize() {

        Node temp = head;
        int i = 0;
        while (temp.next == null)
            temp = temp.next;
        i++;
        return i;
    }

    public void printAll() {
        Node temp = head;
        while ( temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }

    }

    public T get(int index) {
        Node temp = head;
        for ( int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.data;
    }



}
