import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Knapsack {
	static int N;

	public static int[][] Knapsack(int val[], int wt[], int W) {
		N = wt.length; // ������ ����-1
		int[][] V = new int[N + 1][W + 1];// table �迭
		for (int col = 0; col <= W; col++) { // ù ���� ���� 0���� ä���.
			V[0][col] = 0;
		}
		for (int row = 0; row <= N; row++) { // ù��° ���� ���� 0���� ä���.
			V[row][0] = 0;
		}

		for (int i = 1; i <= N; i++) { // ���� ���ʴ�� �̵�

			for (int w = 1; w <= W; w++) { // ���� ���ʴ�� �̵�
				if (wt[i - 1] > w) { // i��° ������ �� �ִ� ���, ���� �����Ƿ� V[i-1][w]
					V[i][w] = V[i - 1][w];
				} else { // i��° ������ ���� �� �ִ� ���
					V[i][w] = max(V[i - 1][w], val[i - 1] + V[i - 1][w - wt[i - 1]]);
				}
			}
		}

		return V;
	}

	// ����Ʈ ���ִ� �κ�
	public static void print(int[][] V, int[] wt, int[] val, int N, int W) {
		int[] selected = new int[N + 1]; // max ���Ը� �����ϴ� item�� �˾Ƴ��� ���� �迭
		
		int n = N;
		int w = W;
		
		while(n>0 && w>0) { //n,w �Ѵ� 0���� Ŭ ����
			if(V[n][w] != V[n-1][w]) {//�ٷ� ���� ���� ���� ���� �ʴٸ�
				selected[n]=1; //�ش� item�� ����
				w = w-wt[n-1]; //���� �ε����� ���δ�.
			}
			n--;
		}
		for (int[] rows : V) {
			for (int col : rows) {
				System.out.format("%3d", col); // �� �پ� ���
			}
			System.out.println();
		}
		System.out.println("max : " + V[N][W]); //max�� ���
		System.out.print("item : ");
		for (int i = 1; i < N + 1; i++)
			if (selected[i] == 1) //���Ե� item�� ���
				System.out.print(i + " ");
		System.out.println();
	}

	public static int max(int a, int b) { // �� ������ ���ؼ� �� ū ���� ����
		if (a > b)
			return a;
		else
			return b;
	}

	public static void main(String[] args) {
		// txt file to String
		Scanner sc = new Scanner(System.in);
		System.out.print("�賶�� ����� �Է��ϼ���(0~50) : ");
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
