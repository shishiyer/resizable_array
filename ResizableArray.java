package shishiriyer.resizablearray;

import java.util.Arrays;

public class ResizableArray {
	private int[] data;
	private static final int INITIAL_MAX = 4;
	private int size;
	
	public ResizableArray() {
		data = new int[INITIAL_MAX];
		size = 0;
	}
	
	public ResizableArray(int[] values) {
		data = new int[INITIAL_MAX];
		size = values.length;
		resize(size + 1);
		for(int i = 0; i < size; i++) {
			data[i] = values[i];
		}
	}
	
	public ResizableArray(ResizableArray other) {
		data = new int[INITIAL_MAX];
		size = other.size;
		resize(size + 1);
		for(int i = 0; i < size; i++) {
			data[i] = other.get(i);
		}
	}
	
	public String toString() {
		StringBuffer s = new StringBuffer("[");
		
		for(int i = 0; i < size; i++) {
			s.append(data[i] + ", ");
		}
		
		s.delete(s.length() - 2, s.length());
		s.append("]");
		return s.toString();
	}
	
	public void insert(int index, int x) {
		if(index > size) throw new ArrayIndexOutOfBoundsException("Bad index: " + index);
		else {
			resize(size + 1);
			for(int i = size - 1; i >= index; i--) {
				data[i + 1] = data[i];
			}
			
			data[index] = x;
			size++;
		}
	}
	
	private void resize(int newSize) {
		int[] newData = new int[newSize];
		for(int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		
		data = newData;
	}
	
	public int indexOf(int value) {
		int i = 0;
		while(data[i] != value && i < size) {
			i++;
		}
		
		return (i == size) ? -1 : i;
	}
	
	public int get(int index) {
		if(index >= size) throw new ArrayIndexOutOfBoundsException("Bad index: " + index);
		else return data[index];
	}
	
	public int remove(int index) {
		if(index >= size) throw new ArrayIndexOutOfBoundsException("Bad index: " + index);
		else {
			int removedValue = data[index];
			for(int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			
			size--;
			return removedValue;
		}
	}
	
	public int size() {
		return size;
	}
	
	public void add(int value) {
		insert(size, value);
	}
	
	public void add(int[] values) {
		for(int i = size; i < values.length + size; i++) {
			data[i] = values[i];
		}
		
		size += values.length;
	}
	
	public void removeAll(int value) {
		int i = 0;
		while(i < size) {
			if(data[i] == value) {
				remove(i);
			} else i++;
		}
	}
	
	public void replaceAll(int oldValue, int newValue) {
		for(int i = 0; i < size; i++) {
			if(data[i] == oldValue) set(i, newValue);
		}
	}
	
	public void clear() {
		size = 0;
	}
	
	public void reverse() {
		for(int i = 0; i < size / 2; i++) {
			data[i] = data[size - i - 1];
		}
	}
	
	public ResizableArray subArray(int start, int end) {
		int[] array = new int[end - start];
		for(int i = start; i < end; i++) {
			array[i - start] = data[i];
		}
		
		return new ResizableArray(array);
	}
	
	public void set(int index, int value) {
		data[index] = value;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public int[] toArray() {
		int[] array = new int[size];
		for(int i = 0; i < size; i++) {
			array[i] = data[i];
		}
		
		return array;
	}
	
	public void sort() {
		int[] sortedArray = toArray();
		Arrays.sort(sortedArray);
		
		for(int i = 0; i < size; i++) {
			data[i] = sortedArray[i];
		}
	}
	
	public boolean equals(Object o) {
		for(int i = 0; i < size; i++) {
			if(data[i] != ((ResizableArray)o).get(i)) {
				return false;
			}
		}
		
		return true;
	}
}