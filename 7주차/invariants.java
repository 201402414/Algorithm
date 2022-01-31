package week07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class invariants {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data07_a.txt");
		BufferedReader br = new BufferedReader(fr);
		String read = br.readLine();
		String[] arrString = new String[1000000];
		arrString = read.split(", ");
		int[] array = new int[1000000];
		
		for (int i = 0; i < arrString.length; i++) {
			array[i] = Integer.parseInt(arrString[i]);
		}
		FileReader fr2 = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data07_b.txt");
		BufferedReader br2 = new BufferedReader(fr2);
		String read2 = br2.readLine();
		arrString = read2.split(", ");
		int[] array2 = new int[1000000];
		
		for (int i = 0; i < arrString.length; i++) {
			array2[i] = Integer.parseInt(arrString[i]);
		}
		
		for(int i = 0 ; i < 10; i++) {
			System.out.print(array2[i]+",");
		}
		
		array = Sort(array,arrString.length);
		
		Scanner input = new Scanner(System.in);
		System.out.print("찾을 숫자를 입력하시오 : ");
		int temp = input.nextInt();
		int result = binarySearch(array, arrString.length, temp);
		if (result == -1) {
			System.out.println("배열에 존재하지 않습니다.");
		} else {
			System.out.println(temp + "는 " + result + "번쨰 인덱스에 존재합니다.");
		}
	}

	public static int[] Sort(int[] A, int size) {
		for (int j = 1; j < size; j++) {
			int key = A[j];
			int i = j - 1;

			while (i >= 0 && A[i] > key) {
				A[i + 1] = A[i];
				i = i - 1;
			}
			A[i + 1] = key;
		}
		return A;

	}

	public static int binarySearch(int[] arr, int size, int n) {
		int first = 0;
		int last = size - 1;
		int mid = 0;
		while (first <= last) {
			mid = (first + last) / 2;
			if (arr[mid] == n) {
				return mid;
			}
			if (arr[mid] > n) {
				last = mid - 1;
			} else {
				first = mid + 1;
			}
		}
		return -1;
	}

}
