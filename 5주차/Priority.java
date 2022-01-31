package week05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.StringTokenizer;

import testheap.Heap;

public class Priority {
	public static void main(String[] args) throws IOException {
		FileReader fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\week05\\data05.txt");
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();

		int key;
		String value;

		ArrayList<Integer> list = new ArrayList<Integer>();
		Hashtable<Integer, String> table = new Hashtable<Integer, String>();

		try {
			while (line != null) {
				StringTokenizer st = new StringTokenizer(line, ",");

				key = Integer.parseInt(st.nextToken());
				value = st.nextToken();
				table.put(key, value);
				list.add(key);
				line = br.readLine();
			}

			build_max_heap(list, list.size());
			Scanner input = new Scanner(System.in);
			Scanner input1 = new Scanner(System.in);
			int input_key;
			String input_value;
			int number;
			while (true) {

				printHeap(list, table);

				System.out.println("\n----------------------------------------");
				System.out.println(" 1. 작업 추가   2. 최대값  3.최대 우선순위 작업 처리 \n 4. 원소 키값 증가	     5. 작업 제거      6.종료");
				System.out.println("----------------------------------------");
				number = input.nextInt();

				if (number == 1) {
					System.out.println("추가할 키값 : ");
					input_key = input.nextInt();
					if (!table.containsKey(input_key)) {
						System.out.println("작업명 : ");
						input_value = input1.nextLine();
						table.put(input_key, " " + input_value);
						insert(list, input_key);

					} else {
						System.out.println("키값이 이미 존재합니다.\n");
					}

				} else if (number == 2) {
					System.out.printf("%d,%s\n\n", max(list), table.get(max(list)));
				} else if (number == 3) {
					if (list.size() != 0) {
						extract_max(list);
						System.out.println("");
					} else {
						System.out.println("작업 대기 목록이 비어있습니다.\n");
					}

				}

				else if (number == 4) {
					int change;
					System.out.println("변경할 키값 : ");
					input_key = input.nextInt();
					if (table.containsKey(input_key)) {

						System.out.println("증가한 키값 : ");
						change = input.nextInt();
						if (change < input_key) {
							System.out.println("키값은 작아질 수 없습니다.\n");
						} else {
							increase_key(list, input_key, change);
							table.put(change, table.get(input_key));
							table.remove(input_key);
							System.out.println("");
						}
					} else {
						System.out.println("선택한 키값이 존재하지 않습니다.");
					}
				} else if (number == 5) {
					System.out.println("제거할 키값 : ");
					int x = input.nextInt();
					if (table.containsKey(x)) {
						System.out.println(table.get(x) + "(이)가 제거되었습니다.");
						delete(list, x);
					} else {
						System.out.println("선택한 키값이 존재하지 않습니다.");
					}

					System.out.println("");
				} else if (number == 6) {
					break;
				} else {
					System.out.println("잘못된 값이 입력되었습니다.\n");
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void increase_key(ArrayList<Integer> S, int x, int k) {
		int count = 0;
		for (int i = 0; i < S.size(); i++) {
			if (S.get(i) == x) {
				break;
			}
			count++;
		}
		S.set(count, k);
		build_max_heap(S, S.size());
	}

	public static void insert(ArrayList<Integer> S, int x) {
		S.add(x);
		build_max_heap(S, S.size());
	}

	public static int max(ArrayList<Integer> S) {
		return S.get(0);
	}

	public static void extract_max(ArrayList<Integer> S) {
		Collections.swap(S, 0, S.size() - 1);
		S.remove(S.size() - 1);
		build_max_heap(S, S.size());
	}

	public static void delete(ArrayList<Integer> S, int x) {
		int count = 0;
		for (int i = 0; i < S.size(); i++) {
			if (S.get(i) == x) {
				break;
			}
			count++;
		}
		S.remove(count);
		build_max_heap(S, S.size());
	}

	ArrayList<Integer> list = new ArrayList<Integer>();
	int size = 0;

	
	public static void max_heapify(ArrayList<Integer> list, int i) {
		int largest = i;
		int L = left_child(i);
		int R = right_child(i);
		int heap_size = list.size();

		if ((L < heap_size) && (list.get(L) > list.get(i))) {
			largest = L;
		} else {
			largest = i;
		}
		if ((R < heap_size) && (list.get(R) > list.get(largest))) {
			largest = R;
		}
		if (largest != i) {
			Collections.swap(list, i, largest);
			max_heapify(list, largest);
		}
	}

	public static int parent(int i) {
		return i / 2;
	}

	public static int left_child(int i) {
		return 2 * i + 1;
	}

	public static int right_child(int i) {
		return 2 * i + 2;
	}

	public static void build_max_heap(ArrayList<Integer> list, int size) {
		for (int i = size / 2; i >= 0; i--) {
			max_heapify(list, i);
		}
	}

	public static void printHeap(ArrayList<Integer> list, Hashtable<Integer, String> table) {
		System.out.println("**** 현재 우선 순위 큐에 저장되어 있는 작업 대기 목록은 다음과 같습니다. ****\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%4d,%s\n", list.get(i), table.get(list.get(i)));
		}
	}
}
