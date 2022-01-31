package week10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Knapsack {

	public static void main(String[] args) {

		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data10_knapsack.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int[] input_item = new int[100];
			int[] input_value = new int[100];
			int[] input_weight = new int[100];

			int index = 0;

			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					input_item[index] = Integer.parseInt(st.nextToken());
					input_value[index] = Integer.parseInt(st.nextToken());
					input_weight[index] = Integer.parseInt(st.nextToken());
					index++;
				}
				line = br.readLine();
			}
			int[] itemNum = new int[index];
			int[] value = new int[index];
			int[] weight = new int[index];

			for (int i = 0; i < index; i++) {
				itemNum[i] = input_item[i];
				value[i] = input_value[i];
				weight[i] = input_weight[i];

			}

			Scanner input = new Scanner(System.in);
			System.out.print("배낭의 사이즈를 입력하세요(0~50) :");
			int size = input.nextInt();

			

			if (size < 0 || size > 50) {
				System.out.println("사이즈 설정이 잘못되었습니다.");
			} else {
				int[][] knap = OPT(value, weight, size);
				print(knap, weight, value, size);

			}
		} catch (IOException e) {

		}
	}

	public static int[][] OPT(int value[], int weight[], int size) {

		int[][] table = new int[weight.length + 1][size + 1];

		for (int j = 0; j <= size; j++) {
			for (int i = 0; i <= weight.length; i++) {
				table[i][j] = 0;
			}
		}

		for (int i = 1; i <= weight.length; i++) { 
			for (int w = 1; w <= size; w++) { 
				if (weight[i - 1] > w) {
					table[i][w] = table[i - 1][w];
				} else { 
					table[i][w] = max(table[i - 1][w], value[i - 1] + table[i - 1][w - weight[i - 1]]);
				}
			}
		}
		return table;
	}

	public static int max(int a, int b) {
		if (a < b)
			return b;
		else
			return a;
	}
	public static void print(int[][] knap, int[] weight, int[] value, int size) {
		int n = weight.length;
		int w = size;
		boolean[] isitem = new boolean[weight.length + 1]; 

		for (int i = 0; i < weight.length + 1; i++) {
			for (int j = 0; j < size + 1; j++) {
				System.out.printf("%3d", knap[i][j]);
			}
			System.out.println();
		}

		System.out.println("max : " + knap[weight.length][size]);

		while (n > 0 && w > 0) { 
			if (knap[n][w] != knap[n - 1][w]) {
				isitem[n] = true; 
				w = w - weight[n - 1]; 
			}
			n--;
		}

		System.out.print("item : ");
		for (int i = 1; i < weight.length + 1; i++) {
			if (isitem[i] == true)
				System.out.print(i + " ");
		}
	}

}
