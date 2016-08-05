/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}
	

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
		if (list.size() == 1) return list; 
		
        List<T> left = list.subList(0,list.size()/2); 
        List<T> right = list.subList(list.size()/2, list.size()); 
        
        left = mergeSort(left, comparator); 
        right = mergeSort(right, comparator); 
        
        List<T> sortedList = merge(left, right, comparator); 
        return sortedList;
	}
	
	private List<T> merge(List<T> left, List<T> right, Comparator<T> comparator){
		List<T> mergedList = new LinkedList<T>(); 
		
		int leftIndex = 0, rightIndex = 0;
		
		while (leftIndex < left.size() && rightIndex < right.size()){
			T leftElement = left.get(leftIndex); 
			T rightElement = right.get(rightIndex); 
			
			if (comparator.compare(leftElement, rightElement) <= 0){
				mergedList.add(leftElement); 
				leftIndex++; 
			} else {
				mergedList.add(rightElement); 
				rightIndex++; 
			}
		}
		
		while (leftIndex < left.size()){
			mergedList.add(left.get(leftIndex)); 
			leftIndex++; 
		}
		
		while (rightIndex < right.size()){
			mergedList.add(right.get(rightIndex)); 
			rightIndex++; 
		}
		
		return mergedList; 
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> heap = new PriorityQueue<T>(list.size(), comparator); 
        
        heap.addAll(list); 
        
        list.clear();  
        
        while (!heap.isEmpty()){
        	list.add(heap.poll()); 
        }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
		if (k > list.size()){
			return null; 
		}
		
        List<T> temp = new LinkedList<T>(list);
        heapSort(temp, comparator);
        
        LinkedList<T> topK = new LinkedList<T>(); 
        for (int i = temp.size()-1; i >= temp.size()-k; i--){
        	topK.addFirst(temp.get(i)); 
        }
        
        return topK;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
