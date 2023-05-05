package Estructuras.APriorityQueue;

import java.io.Serializable;

public interface QueueInterface<T> extends Serializable {
	public void clear();
	public boolean isEmpty();
	public T extract();
	public boolean insert(Object object, int prioridad);
	public int size();
	public boolean search(T object);
	public void sort();
	public void reverse();
	public String toString();
}
