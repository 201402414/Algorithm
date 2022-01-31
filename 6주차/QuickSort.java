package week06;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class QuickSort {
	public static void main(String[] args) throws IOException {

		FileReader fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data06.txt");
		BufferedReader br = new BufferedReader(fr);
		String read = br.readLine();
		String[] arrString = new String[1000000];
		arrString = read.split(",");
		int[] array = new int[1000000];
		int[] array2 = new int [1000000];
		
		for (int i = 0; i < arrString.length; i++) {
			array[i] = Integer.parseInt(arrString[i]);
			array2[i] = Integer.parseInt(arrString[i]);
		}
		BufferedOutputStream bs = null;
		
		sort(array, 0, arrString.length);
		try {
			bs = new BufferedOutputStream(new FileOutputStream("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data6_quick.txt"));
			String str = Integer.toString(array[1]);
			for(int i = 2; i < arrString.length+1; i++) {
				str = str +","+ array[i];
			}
			bs.write(str.getBytes()); 

		} catch (Exception e) {
	                e.getStackTrace();
			// TODO: handle exception
		}finally {
			bs.close(); 
		} 
		
		
		quicksort_withrandom(array2,0,arrString.length);
		try {
			bs = new BufferedOutputStream(new FileOutputStream("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data6_quickRandom.txt"));
			String str = Integer.toString(array[1]);
			for(int i = 2; i < arrString.length+1; i++) {
				str = str +","+ array[i];
			}
			bs.write(str.getBytes()); 

		} catch (Exception e) {
	                e.getStackTrace();
			// TODO: handle exception
		}finally {
			bs.close(); 
		} 
	}
	
	public static void sort(int[] arr, int p, int r) {
		if(p < r) {
			int q = partition(arr,p,r);
			sort(arr,p,q-1);
			sort(arr,q+1,r);
		}
		
	}
	public static void quicksort_withrandom(int[] arr , int p , int r) {
		int q = 0;
		if(p < r) {
			q = randonized_partition(arr,p,r);
			quicksort_withrandom(arr,p,q-1);
			quicksort_withrandom(arr,q+1,r);
		}
	}
	
	public static int partition(int[] arr, int p, int r) {
		int x = arr[r];
		int i = p-1;
		for(int j = p; j <= r-1 ; j++) {
			if(arr[j] <= x) {
				i = i + 1;
				swap(arr, i, j);
			}
		}
		i = i +1;
		swap(arr, i, r);
		return i;
	}
	
	public static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static int randonized_partition(int[] arr, int p, int r){
		Random rand= new Random(); 
		int i = rand.nextInt(r-p) + p; 
		swap(arr, r, i);
		return partition(arr,p,r);
	}
	
	
}
