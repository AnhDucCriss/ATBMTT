package BTThucHanh;

import java.util.Scanner;

public class Affine {
	//k(17,20)
	static final int a = 17;
	static final int b = 20;

	static String encrypt(String msg) {
		StringBuilder cipher = new StringBuilder();
		for (int i = 0; i < msg.length(); i++) {
			if (msg.charAt(i) != ' ') {
				cipher.append((char) (((a * (msg.charAt(i) - 'A') + b) % 26) + 'A'));
			} else {
				cipher.append(' ');
			}
		}
		return cipher.toString();
	}

	static String decrypt(String cipher) {
		StringBuilder msg = new StringBuilder();
		int a_inv = 0;
		int flag = 0;
		for (int i = 0; i < 26; i++) {
			flag = (a * i) % 26;
			if (flag == 1) {
				a_inv = i;
			}
		}
		for (int i = 0; i < cipher.length(); i++) {
			if (cipher.charAt(i) != ' ') {
				msg.append((char) ((a_inv * (cipher.charAt(i) + 'A' - b) % 26) + 'A'));
			} else {
				msg.append(' ');
			}
		}
		return msg.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Nhap message:");
		String msg = scanner.nextLine();
		String cipherText = encrypt(msg);
		System.out.println("Encrypted Message is: " + cipherText);
		String decryptedText = decrypt(cipherText);
		System.out.println("Decrypted message is: " + decryptedText);
		System.out.print("Nhap test: ");
		String test = scanner.nextLine();
		if (test.equals(decryptedText)) {
			System.out.println("Dung");
		} else {
			System.out.println("Sai");
		}
	}
}
