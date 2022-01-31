import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Knapsack {
	static int N;

	public static int[][] Knapsack(int val[], int wt[], int W) {
		N = wt.length; // 세로의 길이-1
		int[][] V = new int[N + 1][W + 1];// table 배열
		for (int col = 0; col <= W; col++) { // 첫 행을 전부 0으로 채운다.
			V[0][col] = 0;
		}
		for (int row = 0; row <= N; row++) { // 첫번째 열을 전부 0으로 채운다.
			V[row][0] = 0;
		}

		for (int i = 1; i <= N; i++) { // 행을 차례대로 이동

			for (int w = 1; w <= W; w++) { // 열을 차례대로 이동
				if (wt[i - 1] > w) { // i번째 물건을 못 넣는 경우, 변함 없으므로 V[i-1][w]
					V[i][w] = V[i - 1][w];
				} else { // i번째 물건을 넣을 수 있는 경우
					V[i][w] = max(V[i - 1][w], val[i - 1] + V[i - 1][w - wt[i - 1]]);
				}
			}
		}

		return V;
	}

	// 프린트 해주는 부분
	public static void print(int[][] V, int[] wt, int[] val, int N, int W) {
		int[] selected = new int[N + 1]; // max 무게를 형성하는 item을 알아내기 위한 배열
		
		int n = N;
		int w = W;
		
		while(n>0 && w>0) { //n,w 둘다 0보다 클 동안
			if(V[n][w] != V[n-1][w]) {//바로 위의 행의 값과 같지 않다면
				selected[n]=1; //해당 item을 포함
				w = w-wt[n-1]; //열의 인덱스를 줄인다.
			}
			n--;
		}
		for (int[] rows : V) {
			for (int col : rows) {
				System.out.format("%3d", col); // 한 줄씩 출력
			}
			System.out.println();
		}
		System.out.println("max : " + V[N][W]); //max값 출력
		System.out.print("item : ");
		for (int i = 1; i < N + 1; i++)
			if (selected[i] == 1) //포함된 item만 출력
				System.out.print(i + " ");
		System.out.println();
	}

	public static int max(int a, int b) { // 두 정수를 비교해서 더 큰 수를 리턴
		if (a > b)
			return a;
		else
			return b;
	}

	public static void main(String[] args) {
		// txt file to String
		Scanner sc = new Scanner(System.in);
		System.out.print("배낭의 사이즈를 입력하세요(0~50) : ");
		int W = sc.nextInt();
		FileReader fr;
		try {
			fr = new FileReader("data11.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();

			int index = 0;
			int[] wt = new int[5];
			int[] val = new int[5];
			int[] item = new int[5];
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					item[index] = Integer.parseInt(st.nextToken());
					val[index] = Integer.parseInt(st.nextToken());
					wt[index++] = Integer.parseInt(st.nextToken());
				}
				line = br.readLine();
			}
			print(Knapsack(val, wt, W),wt,val,N,W);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
