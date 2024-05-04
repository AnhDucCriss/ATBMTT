package BTThucHanh;

public class Oclit {
	static int x = 0, y = 1;

	static int gcdExtended(int a, int b) {
		if (a == 0) {
			x = 0;
			y = 1;
			return b;
		}

		// To store results of recursive call
		int gcd = gcdExtended(b % a, a);
		int x1 = x;
		int y1 = y;

		// Update x and y using results of recursive call
		x = y1 - (b / a) * x1;
		y = x1;
		System.out.println(x+" và "+y+" là: "+gcd);
		return gcd;
	}

	static void modInverse(int A, int M) {
		int g = gcdExtended(A, M);
		System.out.println(g);
		if (g != 1) {
			System.out.println("Nghịch đảo không tồn tại!");
		} else {
			// m is added to handle negative x
			int res = (x % M + M) % M;
			System.out.println("Số nghịch đảo của phép nhân mo-dun là: " + res);
		}
	}

	public static void main(String[] args) {
		int A = 120;
		int M = 347;
		// Function call
		modInverse(A, M);
	}
}
