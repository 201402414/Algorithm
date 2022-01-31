package week13;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Node {
	public int freq;
	public char alphabet;
	public Node leftNode;
	public Node rightNode;

	public Node(int freq, char alphabet) {
		this.freq = freq;
		this.alphabet = alphabet;
		leftNode = rightNode = null;
	}
}

class MinHeap {
	private ArrayList<Node> tree = new ArrayList<Node>(54);

	public MinHeap() {
		tree.add(null);
	}

	public void insert(Node n) {
		tree.add(n);

		int childt = tree.size() - 1;
		int parentt = childt / 2;

		while (parentt >= 1 && tree.get(childt).freq < tree.get(parentt).freq) {
			Collections.swap(tree, childt, parentt);

			childt = parentt;
			parentt = childt / 2;
		}
	}

	public boolean isEmpty() {
		return (tree.size() <= 1);
	}

	public Node extractMinNode() {
		if (isEmpty())
			return null;

		Node min = tree.get(1);

		int top = tree.size() - 1;

		tree.set(1, tree.get(top));
		tree.remove(top);

		int parentt = 1;
		int leftPos = parentt * 2;
		int rightPos = parentt * 2 + 1;

		while (leftPos <= tree.size() - 1) {
			int targetPos;
			if (rightPos > tree.size() - 1) {
				if (tree.get(leftPos).freq >= tree.get(parentt).freq) 
					break;
				targetPos = leftPos;
			} else {
				if (tree.get(leftPos).freq >= tree.get(parentt).freq
						&& tree.get(rightPos).freq >= tree.get(parentt).freq)
					break;

				targetPos = (tree.get(leftPos).freq < tree.get(rightPos).freq) ? leftPos : rightPos;
			}

			Collections.swap(tree, targetPos, parentt);

			parentt = targetPos;
			leftPos = parentt * 2;
			rightPos = parentt * 2 + 1;
		}
		return min;
	}

	public void printTree() {
		for (Node n : tree)
			if (n != null)
				System.out.print(n.freq + " ");
		System.out.println("");
	}
}

public class HuffmanTree {

	public static HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
	public static Node huffmanTree = null;

	public static void countAlphabetFrequency(String src) {
		try {

			BufferedReader in = new BufferedReader(new FileReader(src));
			String s;

			while ((s = in.readLine()) != null) {

				for (int i = 0; i < s.length(); i++) {
					char c = s.charAt(i);
					if (freq.containsKey(c))
						freq.put(c, freq.get(c) + 1);
					else
						freq.put(c, 1);
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}
	}

	public static void makeHuffmanTree() {
		MinHeap mh = new MinHeap();

		if (freq.isEmpty())
			return;

		for (char key : freq.keySet())
			mh.insert(new Node(freq.get(key), key));

		while (true) {

			Node leftChild = mh.extractMinNode();
			Node rightChild = mh.extractMinNode();

			huffmanTree = new Node(leftChild.freq + rightChild.freq, '.');

			huffmanTree.leftNode = leftChild;
			huffmanTree.rightNode = rightChild;

			if (mh.isEmpty()) {
				return;
			}
			mh.insert(huffmanTree);
		}
	}

	static String[] str2 = new String[6];
	static int count = 0;
	static char[] str1 = new char[6];

	public static void printEachCharacterCode(Node htRoot, int[] trace, int top) {

		if (htRoot.leftNode != null) {
			trace[top] = 0;
			printEachCharacterCode(htRoot.leftNode, trace, top + 1);
		}
		if (htRoot.rightNode != null) {
			trace[top] = 1;
			printEachCharacterCode(htRoot.rightNode, trace, top + 1);
		}

		if (htRoot.leftNode == null && htRoot.rightNode == null) 
		{
			str1[count] = htRoot.alphabet;
			str2[count] = printArr(trace, top);
			count++;
		}

	}

	public static String printArr(int[] arr, int top) {
		String temp = "";
		for (int i = 0; i < top; i++) {
			temp = temp + arr[i];
		}
		return temp;
	}

	public static void main(String[] args) throws IOException {
		countAlphabetFrequency("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data13_huffman.txt");
		makeHuffmanTree();

		int[] arr = new int[freq.size() - 1];

		printEachCharacterCode(huffmanTree, arr, 0);

		BufferedOutputStream bs = null;

		try {
			bs = new BufferedOutputStream(
					new FileOutputStream("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\201402414_table.txt"));
			String str = "";
			for (int i = 0; i < 6; i++) {
				str = str + str1[i] + "," + str2[i] + "\n";
			}
			bs.write(str.getBytes());

		} catch (Exception e) {
			e.getStackTrace();
			// TODO: handle exception
		} finally {
			bs.close();
		}

		String change = "";
		FileReader fr;
		try {
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data13_huffman.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();

			char[] ch = new char[line.length()];
			for (int i = 0; i < line.length(); i++) {
				ch[i] = line.charAt(i);
			}

			for (int i = 0; i < line.length(); i++) {
				for (int j = 0; j < 6; j++) {
					if (ch[i] == str1[j]) {
						change = change + str2[j];
					}
				}
			}

		} catch (IOException e) {

		}

		try {
			bs = new BufferedOutputStream(
					new FileOutputStream("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\201402414_encoded.txt"));

			bs.write(change.getBytes());

		} catch (Exception e) {
			e.getStackTrace();
			// TODO: handle exception
		} finally {
			bs.close();
		}
		System.out.println(change.length());
	}
}