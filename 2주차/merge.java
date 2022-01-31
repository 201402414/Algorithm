package H02;

import java.util.Scanner;
import java.util.Arrays;

public class merge {
	
	public static void main(String[] args) {
		
		int[] mergearr = {1381,20144,2937,8401,31904,22750,27539,6615,1492,8110,12833,11891,25449,14327,19563,21346,16756,16012,16590,7966,8155,10696,2560,18444,10171,22890,14236,21239,28678,22691,30682,1469,30065,1646,28317,29256,18829,6176,32180,11712,15667,10816,25177,2047,2598,21400,19454,22342,16372,28300};
		
		mergesort(mergearr, 0, mergearr.length - 1);
		System.out.println("�������� : " + Arrays.toString(mergearr));
	}
	
	public static void mergesort(int mergearr[], int arr1, int arr2) { //����
		if (arr1 < arr2) {
			int middle = (arr1 + arr2) / 2;
			mergesort(mergearr, arr1, middle); // �չ迭 ����
			mergesort(mergearr, middle + 1, arr2); // �޹迭 ����
			merge(mergearr, arr1, middle, arr2); // ��,�� �迭�� ������ �����Լ��� �Ѿ
		} else {
			return;
		}
	}

	public static void merge(int mergearr[], int arr1, int middle, int arr2) { //�պ�
		int i = arr1,  	    // ù��° ������ġ
			j = middle + 1, // �ι�° ������ġ
			k = arr1;		
		int result[] = new int[mergearr.length];
		while (i <= middle && j <= arr2) {
			if (mergearr[i] <= mergearr[j]) {
				result[k++] = mergearr[i++];
			} else {
				result[k++] = mergearr[j++];
			}
		}
		while (i <= middle)
			result[k++] = mergearr[i++];
		while (j <= arr2)
			result[k++] = mergearr[j++];
		for (i = arr1; i <= arr2; i++)
			mergearr[i] = result[i];
	}
}
