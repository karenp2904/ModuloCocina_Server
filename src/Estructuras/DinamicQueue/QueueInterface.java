package Estructuras.DinamicQueue;


import java.io.Serial;
import java.io.Serializable;

public interface QueueInterface<T> extends Serializable {
	public void clear();
	public boolean insert(T object);
	public T extract();

	public boolean isEmpty();
	public int size();
	public boolean search(T object);
	public void sort();
	public void reverse();
	public String toString();
}
