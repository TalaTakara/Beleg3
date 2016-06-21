package List;


public interface Listable <T> {

    public void add(T data);
    public void clear(T data);
    public boolean isEmpty(T data);
    //	public void insertAt(int index, T data);
    public void remove(int indes);
    public int getsize();
    public void printAll();
    public T get(int index);


}
