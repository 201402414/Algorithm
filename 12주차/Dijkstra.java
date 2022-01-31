package week12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Dijkstra {

	public static void main(String[] args) {

		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data12.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int[] array1 = new int[32];
			int index = 0;
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					array1[index] = Integer.parseInt(st.nextToken());
					index++;
				}
				line = br.readLine();
			}
			int[] static_point = new int[5];
			for (int i = 0; i < 5; i++) {
				static_point[i] = array1[i];
			}
			int[] array2 = new int[27];
			for (int i = 5; i < 32; i++) {
				array2[i - 5] = array1[i];
			}
			int[] start_point = new int[9];
			int[] end_point = new int[9];
			int[] weight = new int[9];

			int index_count = 0;
			for (int i = 0; i < 27; i = i + 3) {
				start_point[index_count] = array2[i];
				end_point[index_count] = array2[i + 1];
				weight[index_count] = array2[i + 2];
				index_count++;
			}
			char[] S = new char[static_point.length];
			for (int i = 0; i < static_point.length; i++) {
				S[i] = alp(static_point[i]);
			}

			Dijkstra di = new Dijkstra(S);

			for (int i = 0; i < start_point.length; i++) {
				di.add(alp(start_point[i]), alp(end_point[i]), weight[i]);
			}
			di.Dijkstra_Alg(S);

		} catch (IOException e) {

		}

	}

	char[] Vertices;
	int[][] Di_arr;

	public static char alp(int i) {
		return (char) (i + 64);
	}
	
	public Dijkstra(char[] static_arr) {
		int size = static_arr.length;
		Vertices = static_arr;
		Di_arr = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++) {
				Di_arr[i][j] = Integer.MAX_VALUE;
				if (i == j) {
					Di_arr[i][j] = 0;
				}
			}

	}

	public void Dijkstra_Alg(char[] static_point) {

		int size = static_point.length;
		int[] Distance = new int[size];
		Distance[0] = 0; 
		int count1 = 0;
		Point u, v; 
		PriorityQueue<Point> pri = new PriorityQueue<>();

		for (int n = 1; n < size; n++) {
			Distance[n] = Integer.MAX_VALUE;
		}
		for (int n = 0; n < size; n++) { 
			pri.add(new Point(Distance[n], Vertices[n]));
		}
		while (!pri.isEmpty()) {
			int count = 0;
			u = pri.remove(); 
			System.out.println("---------------------------------------------------");
			System.out.println("S[" + count1++ + "] : " + "d[" + u.vertex + "] = " + u.dis);
			System.out.println("---------------------------------------------------");

			Iterator<Point> it = pri.iterator(); 
			while (it.hasNext()) {
				v = it.next(); 
				int x = u.vertex - 'A'; 
				int y = v.vertex - 'A';
				System.out.print("Q[" + count++ + "] : " + "d[" + v.vertex + "] " + "= " + v.dis);
				if (u.dis + Di_arr[x][y] < v.dis && Di_arr[x][y] != Integer.MAX_VALUE) {
					v.dis = u.dis + Di_arr[x][y];			
					System.out.print(" -> " + "d[" + v.vertex + "] " + "= " + v.dis);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();

			PriorityQueue<Point> temp = new PriorityQueue<>(); 
			while (!pri.isEmpty()) {
				temp.add(pri.remove());
			}
			pri = temp; 

		}
	}

	public void add(char v, char w, int weight) {// 임접한 원소에 방향과 가중치를 추가

		int i = index(v), j = index(w);

		Di_arr[i][j] = Di_arr[j][i] = weight; // 가중치 추가
	}

	private int index(char static_index) { // 정점에 대한 인덱스를 받아오는 메소드
		for (int i = 0; i < 5; i++) {
			if (Vertices[i] == static_index) {
				return i;
			}
		}
		return Di_arr.length;
	}

}

class Point implements Comparable<Point> {
	int dis;
	char vertex;

	public Point(int dis, char vertex) { // 거리와 정점을 다 가지는 Point
		this.dis = dis;
		this.vertex = vertex;
	}

	public int compareTo(Point cond) { // 우선순위 큐 안의 조건
		if (dis <= cond.dis) {
			return -1;
		}
		return 1;
	}
}
