package H03;
import java.util.Scanner;

public class power {
	public static void main(String[] args) {
		int a,n;
		Scanner input = new Scanner(System.in);
		System.out.print("a �Է� : ");
		a = input.nextInt();
		System.out.print("n �Է� : ");
		n = input.nextInt();
		
		System.out.println("��� : "+powerNumber(a,n));
	}

	public static int powerNumber(int a, int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return a;
		} else if (n % 2 == 0) {
			return (int) powerNumber(a, n / 2) * powerNumber(a, n / 2);
		} else if (n % 2 != 0) {
			return (int) powerNumber(a, (n - 1) / 2) * powerNumber(a, (n - 1) / 2) * a;
		}
		return n;
	}
}