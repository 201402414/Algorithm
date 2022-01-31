package week11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BellmanFord {

	class Edge {
		int src, dest, weight;

		Edge() {
			src = 0;
			dest = 0;
			weight = 0;

		}
	};

	int V, E;
	Edge edge[];

	BellmanFord(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i = 0; i < e; i++) {
			edge[i] = new Edge();
		}
	}

	void BellmanFord_(BellmanFord bf, int src) {
		int V = bf.V, E = bf.E;
		int dist[] = new int[V];
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[src] = 0;
		for (int i = 0; i < V; i++) {
			System.out.printf("----------------- Iteration %d -----------------\n", i);
			for (int j = 0; j < E; j++) {
				int u = bf.edge[j].src;
				int v = bf.edge[j].dest;
				int weight = bf.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
					int temp = dist[v];

					dist[v] = dist[u] + weight;
					if (temp == Integer.MAX_VALUE) {
						System.out.printf("Update distance of %d from inf to %d \n", v, dist[v]);
					} else {
						System.out.printf("Update distance of %d from %d to %d \n", v, temp, dist[v]);
					}

				}
			}

			System.out.printf("iteration %d distance : [ ", i);
			for (int k = 0; k < V; k++) {
				System.out.print(dist[k] + " ");
			}
			System.out.print("]\n\n");

		}
		for (int j = 0; j < E; j++) {
			int u = bf.edge[j].src;
			int v = bf.edge[j].dest;
			int weight = bf.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
				System.out.println("The bf has negative cycle");

			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data11_bellman_ford_1.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			int[] array1 = new int[35];
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
			int[] array2 = new int[30];
			for (int i = 5; i < 35; i++) {
				array2[i - 5] = array1[i];
			}
			int[] start_point = new int[10];
			int[] end_point = new int[10];
			int[] weight = new int[10];

			int index_count = 0;
			for (int i = 0; i < 30; i = i + 3) {
				start_point[index_count] = array2[i];
				end_point[index_count] = array2[i + 1];
				weight[index_count] = array2[i + 2];
				index_count++;
			}
			int[][] point = new int[10][3];
			for (int i = 0; i < 10; i++) {
				point[i][0] = start_point[i];
				point[i][1] = end_point[i];
				point[i][2] = weight[i];
			}
			int V = 5, E = 10;
			BellmanFord bf = new BellmanFord(V, E);
			for (int i = 0; i < 10; i++) {
				bf.edge[i].src = point[i][0];
				bf.edge[i].dest = point[i][1];
				bf.edge[i].weight = point[i][2];

			}
			bf.BellmanFord_(bf, 0);

		} catch (IOException e) {

		}

	}

}
