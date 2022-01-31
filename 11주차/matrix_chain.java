package week11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class matrix_chain {

	public static void main(String args[]) {
		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data11_matrix_chain.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int[] matrix = new int[12];

			int index = 0;

			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					matrix[index] = Integer.parseInt(st.nextToken());
					index++;
				}
				line = br.readLine();
			}

			int input[] = new int[7];
			
			for (int i = 0; i < 6; i++) {
				input[i] = matrix[i * 2];
			}
			input[6] = matrix[11];

			print(MATRIX_CHAIN_ORDER(input));

		} catch (IOException e) {

		}
	}

	public static int[][] MATRIX_CHAIN_ORDER(int[] p) {
		int n = p.length - 1;
		int[][] m = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) { 
			m[i][i] = 0;
		}
		for (int l = 2; l <= n; l++) {
			for (int i = 1; i <= n - l + 1; i++) {
				int j = i + l - 1;
				m[i][j] = Integer.MAX_VALUE; 
				for (int k = i; k <= j - 1; k++) {
					int q = m[i][k] + m[k + 1][j] + (p[i - 1] * p[k] * p[j]); 
					if (q < m[i][j]) { 
						m[i][j] = q; 
					}
				}
			}
		}
		
		return m;
	}
	public static void print(int m[][]) {
		int n = m.length - 1;
		
		System.out.println("*********각 단계별로 곱셈의 수를 출력*********");
		
		for (int i = 1; i <= n; i++) { // m배열 출력
			for (int j = 1; j <= n; j++)
				System.out.format("%6d", m[i][j]);
			System.out.println();
		}
		System.out.println("**************************************");
		System.out.println("최소 곱셉의 수 : " + m[1][n]);
	}
}
