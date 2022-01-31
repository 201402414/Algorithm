package week13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Prim {
	public static void main(String[] args) {

		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data13_prim.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			String[] array1 = new String[51];
			int index = 0;
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					array1[index] = st.nextToken();
					index++;
				}
				line = br.readLine();
			}

			String[] static_point = new String[9];
			for (int i = 0; i < 9; i++) {
				static_point[i] = array1[i];
			}
			String[] array2 = new String[42];
			for (int i = 9; i < 51; i++) {
				array2[i - 9] = array1[i];
			}
			String[] start_point = new String[14];
			String[] end_point = new String[14];
			int[] weight = new int[14];

			for (int i = 0, k = 0; i < 14; i++, k = k + 3) {
				start_point[i] = array2[k];
				end_point[i] = array2[k+1];
				weight[i] = Integer.parseInt(array2[k+2]);
			}
			
			Prim MST = new Prim();
			Graph graph = MST.createGraph(9);
			
			for(int i =0; i < 14; i++) {
				MST.add(graph, start_point[i].charAt(0), end_point[i].charAt(0), weight[i]);
			}

			MST.getPrimsMST(graph);

		} catch (IOException e) {

		}

	}

	public class AdjList {
		ArrayList<Node> nodes;
	}

	public class Graph {                                            																							 
		int size;
		AdjList[] adjLists;
	}

	public Graph createGraph(int v) {                                       																				
		Graph graph = new Graph();
		graph.size = v;
		graph.adjLists = new AdjList[v];
		for (int i = 0; i < v; i++) {
			AdjList adjList = new AdjList();
			adjList.nodes = new ArrayList<Node>();
			graph.adjLists[i] = adjList;
		}
		return graph;
	}

	public void add(Graph graph, char src, char dest, int key) {
		Node srcNode = new Node(src, key); 																														
		Node destNode = new Node(dest, key); 																													
		graph.adjLists[src - 'a'].nodes.add(destNode);
		graph.adjLists[dest - 'a'].nodes.add(srcNode);
	}

	public void getPrimsMST(Graph graph) {
		Node keys[] = new Node[graph.size];																															
		char parent[] = new char[graph.size];																														
		boolean mstSet[] = new boolean[graph.size];																													
		int sum = 0; 																																			

		for (int i = 0; i < graph.size; i++) {
			char c = (char) (i + 'a');
			keys[i] = new Node(c, Integer.MAX_VALUE);																										
			mstSet[i] = false;
		}
		keys[0].key = 0;																																			 
		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		pQueue.addAll(Arrays.asList(keys));																														
		while (!pQueue.isEmpty()) {																																		
			Node u = pQueue.remove();																																
			mstSet[u.vertice - 'a'] = true;

																																									
			for (Node v : graph.adjLists[u.vertice - 'a'].nodes) {
																																									
				if (mstSet[v.vertice - 'a'] == false && v.key < keys[v.vertice - 'a'].key) {
					pQueue.remove(keys[v.vertice - 'a']);																													
					keys[v.vertice - 'a'].key = v.key;																														
					parent[v.vertice - 'a'] = u.vertice;																									
					pQueue.add(keys[v.vertice - 'a']);
				}

			}
			sum = sum + keys[u.vertice - 'a'].key;
			System.out.println("w(" + parent[u.vertice - 'a'] + "," + keys[u.vertice - 'a'].vertice + ") = "
					+ keys[u.vertice - 'a'].key);
		}
		System.out.println();
		System.out.println("w(MST) = " + sum);
	}

	public class Node implements Comparable<Node> {																								
		char vertice;
		int key;

		Node(char vertice, int key) {																											
			this.vertice = vertice;
			this.key = key;
		}
		public int compareTo(Node o) {																											
			return this.key - o.key;
		}
	}
}
