package H03;

import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num;
		int tmp;

		System.out.println("방법 \n1 : Recursion\n2 : Array\n3 : Recursive squaring");

		Scanner input = new Scanner(System.in);
		num = input.nextInt();

		System.out.print("출력할 항을 입력하시오 : ");
		tmp = input.nextInt();

		if (num == 1) {
			for (int i = 0; i < tmp + 1; i++) {
				if (i % 10 == 0) {
					System.out.println("-----------------------------------------------------------------------------");
				}
				double beforeTime = System.nanoTime();
				
				BigInteger result = RecursionFibo(i);
				
				double afterTime = System.nanoTime();
				double secDiffTime = (afterTime - beforeTime) / 1000000000; // 두 시간에 차 계산

				System.out.printf("f<%2d> = %-50d %.12f sec\n", i, result, secDiffTime);

			}
		} else if (num == 2) {
			
			BigInteger array[] = new BigInteger[tmp + 1];
			ArrayFibo(array, tmp);
			
		} else if (num == 3) {
			
			for (int i = 0; i < tmp + 1; i++) {
				if (i % 10 == 0) {
					System.out.println("-----------------------------------------------------------------------------");
				}
				double beforeTime = System.nanoTime();
				
				BigInteger result = SquaringFibo(i);
				
				double afterTime = System.nanoTime();
				double secDiffTime = (afterTime - beforeTime) / 1000000000; // 두 시간에 차 계산

				System.out.printf("f<%2d> = %-50d %.12f sec\n", i, result, secDiffTime);
			}
			
		} else {
			System.out.println("잘못 입력하였습니다.");
		}
		//

	}

	public static BigInteger RecursionFibo(int n) {
		if (n < 2) {
			return BigInteger.valueOf(n);

		} else {
			return RecursionFibo(n - 1).add(RecursionFibo(n - 2));
		}
	}

	public static void ArrayFibo(BigInteger[] array, int n) {

		double beforeTime = System.nanoTime();
		array[0] = BigInteger.ZERO;
		array[1] = BigInteger.ONE;
		double afterTime = System.nanoTime();
		double secDiffTime = (afterTime - beforeTime) / 1000000000; // 두 시간에 차 계산
		System.out.println("-----------------------------------------------------------------------------");
		for (int j = 0; j < 2; j++) {
			System.out.printf("f<%2d> = %-50d %.12f sec\n", j, array[j], secDiffTime);
		}

		for (int i = 2; i < array.length; i++) {
			beforeTime = System.nanoTime();
			array[i] = array[i - 1].add(array[i - 2]);
			afterTime = System.nanoTime();
			secDiffTime = (afterTime - beforeTime) / 1000000000; // 두 시간에 차 계산
			if (i % 10 == 0) {
				System.out.println("-----------------------------------------------------------------------------");
			}
			System.out.printf("f<%2d> = %-50d %.12f sec\n", i, array[i], secDiffTime);

		}

	}

	public static BigInteger SquaringFibo(int n) {
		BigInteger[][] Squaring = { { BigInteger.ONE, BigInteger.ONE }, { BigInteger.ONE, BigInteger.ZERO } };
		if (n < 2) {
			return BigInteger.valueOf(n);
		} else {
			Squaring = POW(Squaring, n);
			
			return Squaring[0][1];
		}

	}

	public static BigInteger[][] MUL(BigInteger[][] pro1, BigInteger[][] pro2) {
		BigInteger[][] result = new BigInteger[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				result[i][j] = BigInteger.ZERO;
				for (int k = 0; k < 2; k++) {
					result[i][j] = result[i][j].add(pro1[i][k].multiply(pro2[k][j]));

				}
			}
		}
		
		return result;
	}

	public static BigInteger[][] POW(BigInteger[][] a, int n) {
		if (n == 1) {
			return a;
		} else if (n % 2 == 0) {// 짝수
			return MUL(POW(a, n / 2), POW(a, n / 2));
		} else { // 홀수
			return MUL ( MUL ( POW(a, (n - 1) / 2), POW(a, (n - 1) / 2)), a);
		}
	}
}