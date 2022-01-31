package H02;
import java.util.Scanner;
import java.util.Arrays;

public class Insertion {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int temp;
		
		int  array[] = {1381,20144,2937,8401,31904,22750,27539,6615,1492,8110,12833,11891,25449,14327,19563,21346,16756,16012,16590,7966,8155,10696,2560,18444,10171,22890,14236,21239,28678,22691,30682,1469,30065,1646,28317,29256,18829,6176,32180,11712,15667,10816,25177,2047,2598,21400,19454,22342,16372,28300};
		
		for (int i = 1; i < array.length; i++) {
			int mid = array[i]; 
			int key = i - 1;   

			while (key >= 0 && mid < array[key]) {
				array[key + 1] = array[key];  
				key--;
			}
			array[key + 1] = mid; 
		}
		System.out.println("»ðÀÔÁ¤·Ä : "+Arrays.toString(array));
	}
}
