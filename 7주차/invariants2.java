package week07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class invariants2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int cnt1 = 0, cnt2 = 0;

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
		
		System.out.println(search(array,arrString.length,array2,arrString.length));
	}

	public static int search(int[] A, int sizea, int[] B, int sizeb) {
		int start_a = 0;
		int start_b = 0;
		int last_a = sizea - 1;
		int last_b = sizeb - 1;

		while ((last_a - start_a != 1) && (last_b - start_b != 1)) {
			int mid_a = (start_a + last_a) / 2;
			int mid_b = (start_b + last_b) / 2;
			if (A[mid_a] > B[mid_b]) {
				last_a = mid_a;
				start_b = mid_b;
			}
			if (A[mid_a] < B[mid_b]) {
				start_a = mid_a;
				last_b = mid_b + 1;
			}
		}
		if (A[last_a] < B[last_b]) {
			return A[last_a];
		} else {
			return A[last_b];
		}
	}

}
