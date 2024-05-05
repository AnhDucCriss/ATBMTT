package BTThucHanh;

import java.util.ArrayList;
import java.util.Random;

public class Elgamal {
	static Random random = new Random();

	static int gcd(int a, int b) {
		if (a < b) {
			return gcd(b, a);
		} else if (a % b == 0) {
			return b;
		} else {
			return gcd(b, a % b);
		}
	}

	static int genKey(int q) {
		int key = random.nextInt((int) Math.pow(10, 20));
		while (gcd(q, key) != 1) {
			key = random.nextInt((int) Math.pow(10, 20));
		}
		return key;
	}

	static int power(int a, int b, int c) {
		int x = 1;
		int y = a;
		while (b > 0) {
			if (b % 2 != 0) {
				x = (x * y) % c;
			}
			y = (y * y) % c;
			b = b / 2;
		}
		return x % c;
	}

	static int[] encrypt(String msg, int q, int h, int g) {
		int[] enMsg = new int[msg.length()];

		int k = genKey(q);
		int s = power(h, k, q);
		int p = power(g, k, q);

		for (int i = 0; i < msg.length(); i++) {
			enMsg[i] = s * (int) msg.charAt(i);
		}

		System.out.println("g^k used : " + p);
		System.out.println("g^ak used : " + s);

		return new int[] { p, s };
	}

	static String decrypt(int[] enMsg, int p, int key, int q) {
		StringBuilder drMsg = new StringBuilder();
		int h = power(p, key, q);
		for (int i = 0; i < enMsg.length; i++) {
			drMsg.append((char) (enMsg[i] / h));
		}
		return drMsg.toString();
	}

	 static ArrayList<Character> decrypt(ArrayList<Integer> enMsg, int p, int key, int q) {
	        ArrayList<Character> drMsg = new ArrayList<>();
	        int h = power(p, key, q);
	        for (int i = 0; i < enMsg.size(); i++) {
	            drMsg.add((char) (enMsg.get(i) / h));
	        }
	        return drMsg;
	    }
	
	public static void main(String[] args) {
		String msg = "encryption";
		System.out.println("Original Message: " + msg);

		int q = random.nextInt((int) Math.pow(10, 20));
		int g = random.nextInt(q - 2) + 2;

		int key = genKey(q);
		int h = power(g, key, q);

		System.out.println("g used: " + g);
		System.out.println("g^a used: " + h);

		int[] enMsg = encrypt(msg, q, h, g);
		String drMsg = decrypt(enMsg, enMsg[0], key, q);
		System.out.println("Decrypted Message: " + drMsg);
	}
}
