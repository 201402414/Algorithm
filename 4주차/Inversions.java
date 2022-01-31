import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Inversions {

	public static void main(String[] args) {
		FileReader fr;
		try {
			int index = 0;
			fr = new FileReader("C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data04_inversion.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			StringTokenizer st = new StringTokenizer(line, ",");
			int[] arr = new int[st.countTokens()];
			while (st.hasMoreTokens()) {
				arr[index++] = Integer.parseInt(st.nextToken());
			}
			System.out.print("Input Data : ");
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}

			System.out.println("\nOutput : " + Counting_Inversions(arr, arr.length));

			System.out.print("Sort : ");
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	static int Counting_Inversions(int arr[], int count) {
		int temp[] = new int[count];
		return merge_Sort(arr, temp, 0, count - 1);
	}

	static int merge_Sort(int arr[], int temp[], int left, int right) {
		int mid, inversion_count = 0; 
		if (right > left) {

			mid = (right + left) / 2; 

			inversion_count = merge_Sort(arr, temp, left, mid);
			inversion_count = inversion_count + merge_Sort(arr, temp, mid + 1, right);
			inversion_count = inversion_count + merge(arr, temp, left, mid + 1, right);
		}

		return inversion_count;
	}

	static int merge(int arr[], int result[], int indexA, int indexB, int right) {

		int inversion_count = 0;

		int i = indexA;
		int j = indexB;
		int k = indexA;
		while ((i <= indexB - 1) && (j <= right)) {
			if (arr[i] <= arr[j]) {
				result[k++] = arr[i++];
			} else {

				result[k++] = arr[j++];

				inversion_count = inversion_count + (indexB - i);
			}
		}

		while (i <= indexB - 1)
			result[k++] = arr[i++];

		while (j <= right)
			result[k++] = arr[j++];

		for (i = indexA; i <= right; i++)
			arr[i] = result[i];

		return inversion_count;
	}

}
