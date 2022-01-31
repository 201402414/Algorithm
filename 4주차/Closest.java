import java.io.FileInputStream;
import java.util.*;
import static java.lang.Math.abs;

public class Closest {

	public static void main(String[] args) {

		String str = "";
		try (FileInputStream fstream = new FileInputStream(
				"C:\\Users\\micke\\eclipse-workspace\\ALG\\src\\data04_closest.txt")) {

			byte[] read = new byte[fstream.available()];
			while (fstream.read(read) != -1) {
			}
			fstream.close();
			str = new String(read);
			System.out.println("input :\n" + str);
		} catch (Exception e) {
			e.getStackTrace();
		}

		str = str.replace("\n", ",");
		String[] str_array = str.split(",");
		ArrayList<Location> arrayList = new ArrayList<>();

		for (int i = 0; i < str_array.length; i += 2) {
			Location Location = new Location(Double.parseDouble(str_array[i]), Double.parseDouble(str_array[i + 1]));
			arrayList.add(Location);
		}

		System.out.printf("output : %.3f", Closest_Pair(arrayList));

	}

	public static double Closest_Pair(List<Location> arrayList) {
		if (arrayList.size() <= 3) {
			return Bruteforce(arrayList);
		}

		double left_point = Closest_Pair(arrayList.subList(0, arrayList.size() / 2));

		double right_point = Closest_Pair(arrayList.subList(arrayList.size() / 2, arrayList.size()));
		double Min = Min(left_point, right_point);

		List<Location> List_Location = new ArrayList<>();

		for (int i = 0; i < arrayList.size(); i++) {
			double base = ((int) arrayList.get(arrayList.size() / 2 - 1).getX_Location()
					+ (int) arrayList.get(arrayList.size() / 2).getX_Location()) / 2 - arrayList.get(i).getX_Location();

			if (abs(base) < Min) {
				List_Location.add(arrayList.get(i));
			}
		}

		for (int i = 0; i < sort(List_Location).size() - 1; i++) {

			for (int j = i + 1; j < sort(List_Location).size()
					&& (List_Location.get(j).y_Location - List_Location.get(i).y_Location) < Min; j++) {

				Min = Min(Min, Distance(List_Location.get(i), List_Location.get(j)));
			}
		}
		return Min;
	}

	
	public static double Bruteforce(List<Location> arrayList) {
		double brute = Distance(arrayList.get(0), arrayList.get(1));

		for (int i = 0; i < arrayList.size(); i++) {
			for (int j = 0; j < arrayList.size(); j++) {
				if (i != j) {
					if (brute > Distance(arrayList.get(i), arrayList.get(j))) {
						brute = Distance(arrayList.get(i), arrayList.get(j));
					}

				}
			}
		}
		return brute;
	}

	public static double Min(double tmp1, double tmp2) {
		if (tmp1 > tmp2) {
			return tmp2;
		} else {
			return tmp1;
		}
	}

	static class Location implements Comparable<Location> {

		public double x_Location;
		public double y_Location;

		public Location(double x_Location, double y_Location) {
			this.x_Location = x_Location;
			this.y_Location = y_Location;
		}

		public double getX_Location() {
			return x_Location;
		}

		public double getY_Location() {
			return y_Location;
		}

		public void setX_Location(double x_Location) {
			this.x_Location = x_Location;
		}

		public void setY_Location(double y_Location) {
			this.y_Location = y_Location;
		}

		public int compareTo(Location Location) {
			if (this.x_Location < Location.getX_Location()) {
				return -1;
			} else if (this.x_Location > Location.getX_Location()) {
				return 1;
			}
			return 0;
		}
	}

	public static List<Location> sort(List<Location> L) {
		for (int i = 1; i < L.size(); i++) {
			Location base = L.get(i); 
			int tmp = i - 1; 
			while (tmp >= 0 && base.y_Location < L.get(tmp).y_Location) {
				Location temp = L.get(tmp);
				L.remove(L.get(tmp));
				L.add(tmp + 1, temp);
				tmp--;
			}

			L.add(tmp + 1, base);
			L.remove(base);

		}

		return L;
	}

	// d(a, b) = |xa - xb| + |ya - yb|
	public static double Distance(Location Location1, Location Location2) {
		double distance = Math.sqrt(Math.pow(abs(Location2.getX_Location() - Location1.getX_Location()), 2)
				+ Math.pow(abs(Location2.getY_Location() - Location1.getY_Location()), 2));
		return distance;
	}
}