package week08;

import java.util.ArrayList;
import java.util.Stack;
import java.io.FileInputStream;

public class dynamic {
	public static void main(String[] args) {
		String str = "";
		String myfile = "C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data08.txt";

		try (FileInputStream fstream = new FileInputStream(myfile)) {
			byte[] by = new byte[fstream.available()];
			while (-1 != fstream.read(by)) {
			}
			fstream.close();
			str = new String(by);

		} catch (Exception e) {
			e.getStackTrace();
		}

		str = str.replace("\n", ",");

		String[] strings = str.split(",");
		ArrayList<point> arrayList = new ArrayList<>();
		arrayList.add(new point(0.0, 0.0));

		for (int i = 1; i < strings.length - 1; i = i + 2) {
			point point = new point(Float.parseFloat(strings[i]), Float.parseFloat(strings[i + 1]));
			arrayList.add(point);
		}

		int pc = Integer.parseInt(strings[0]);
		int cost = Integer.parseInt(strings[strings.length - 1]);

		segmented_least_squares(pc, cost, arrayList);

	}

	public static class point {

		private double x;
		private double y;

		public point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		public double getx() {
			return x;
		}

		public double gety() {
			return y;
		}

		public void setx(double x) {
			this.x = x;
		}

		public void sety(double y) {
			this.y = y;
		}

	}

	public static void segmented_least_squares(int n, int cost, ArrayList<point> points) {

		double[] sigx = new double[50];
		double[] sigy = new double[50];
		double[] sigxy = new double[50];
		double[] sigx2 = new double[50];
		double[][] SSE_a = new double[50][50];
		double[][] SSE_b = new double[50][50];
		double[][] E = new double[50][50];
		double[] OPT = new double[50];
		double[] opt_val = new double[50];
		double sx;
		double sy;
		double sxy;
		double sx2;
		int count = 0;
		int opt_i = 1;
		int k;
		double real_min;
		double result;
		int opt_re = 0;
		double min = 0;
		OPT[0] = 0;
		opt_val[0] = 0;

		for (int j = 1; j <= n; j++) {
			sigx[j] = sigx[j - 1] + points.get(j).x;
			sigy[j] = sigy[j - 1] + points.get(j).y;
			sigxy[j] = sigxy[j - 1] + points.get(j).x * points.get(j).y;
			sigx2[j] = sigx2[j - 1] + points.get(j).x * points.get(j).x;
			for (int i = 1; i <= j; i++) {
				sx = sigx[j] - sigx[i - 1];
				sy = sigy[j] - sigy[i - 1];
				sxy = sigxy[j] - sigxy[i - 1];
				sx2 = sigx2[j] - sigx2[i - 1];
				count = j - i + 1;
				SSE_a[i][j] = (((count * sxy) - (sx * sy)) / (count * sx2 - sx * sx));
				SSE_b[i][j] = (sy - (SSE_a[i][j] * sx)) / count;
				for (k = i, E[i][j] = 0.0; k <= j; k++) {
					result = points.get(k).y - (SSE_a[i][j] * points.get(k).x) - SSE_b[i][j];
					E[i][j] = E[i][j] + (result * result);
				}
			}
		}

		for (int j = 1; j <= n; j++) {
			min = 1000000000;
			for (opt_i = 1; opt_i <= j; opt_i++) {
				real_min = E[opt_i][j] + OPT[opt_i - 1];
				if (min > real_min) {
					min = real_min;
					opt_re = opt_i;
				}
			}
			OPT[j] = min + cost;
			opt_val[j] = opt_re;
		}

		System.out.printf("Cost of the optimal solution : %.6f\n", OPT[n]);
		Stack segments = new Stack();
		int first;
		int last;
		for (first = n, last = (int) opt_val[n]; first > 0; first = (last - 1), last = (int) opt_val[first]) {
			segments.push(first);
			segments.push(last);
		}

		System.out.println();
		System.out.println("An optimal solution : ");
		while (!segments.empty()) {
			first = (int) segments.peek();
			segments.pop();
			last = (int) segments.peek();
			segments.pop();
			System.out.printf("[Segment : %d - %d] : y =  %.6f *  x + %.6f // square error : %.6f \n", first, last,
					SSE_a[first][last], SSE_b[first][last], E[first][last]);
		}

	}

}
