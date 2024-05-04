package BTThucHanh;

import java.util.Scanner;

public class Hill {
	static int inverse(int a, int n) {
        int r, q, y0 = 0, y1 = 1;
        int y = 0;
        while (a > 0) {
            r = n % a;
            if (r == 0)  break;
            q = n / a;
            n = a;
            a = r;
            y = y0 - q * y1;
            y0 = y1;
            y1 = y;
        }
        if (a > 1) return -1;
        if (y > 0) return y;
        else return y + 26;
    }

    static void multiplyMatrix(int[] input, int[][] key, int[] result) {
        for (int i = 0; i < 2; i++) {
            int sum = 0;
            for (int j = 0; j < 2; j++) {
                sum += input[j] * key[j][i];
            }
            result[i] = sum % 26;
        }
    }

    static void encrypt(String plaintext, int[][] key, StringBuilder result) {
        int length = plaintext.length();
        for (int i = 0; i < length; i += 2) {
            int[] kq = new int[2];
            int[] vector = new int[2];
            for (int j = 0; j < 2; j++) {
                vector[j] = plaintext.charAt(i + j) - 'A';
            }
            multiplyMatrix(vector, key, kq);

            for (int j = 0; j < 2; j++) {
                result.append((char) (kq[j] + 'A'));
            }
        }
    }

    static void inverseKey(int[][] key, int[][] inverseKey) {
        int det = key[0][0] * key[1][1] - key[0][1] * key[1][0];
        int detnd = inverse(det, 26);

        inverseKey[0][0] = (detnd * key[1][1]) % 26;
        inverseKey[1][1] = (detnd * key[0][0]) % 26;
        inverseKey[0][1] = (detnd * -key[0][1]) % 26;
        if (inverseKey[0][1] < 0) inverseKey[0][1] += 26;
        inverseKey[1][0] = (detnd * -key[1][0]) % 26;
        if (inverseKey[1][0] < 0) inverseKey[1][0] += 26;
    }

    static void decrypt(String ciphertext, int[][] key, StringBuilder result) {
        int[][] inverseKey = new int[2][2];
        inverseKey(key, inverseKey);

        int length = ciphertext.length();
        for (int i = 0; i < length; i += 2) {
            int[] kq = new int[2];
            int[] vector = new int[2];
            for (int j = 0; j < 2; j++) {
                vector[j] = ciphertext.charAt(i + j) - 'A';
            }
            multiplyMatrix(vector, inverseKey, kq);

            for (int j = 0; j < 2; j++) {
                result.append((char) (kq[j] + 'A'));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] key = new int[2][2];

        System.out.println("Nhap khoa");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print("Nhap khoa k: ");
                key[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine(); // consume newline character
        System.out.print("Xau can ma hoa: ");
        String plaintext = scanner.nextLine();
        StringBuilder encryptedText = new StringBuilder();
        encrypt(plaintext, key, encryptedText);
        System.out.println("Xau sau khi ma hoa: " + encryptedText);

        System.out.print("Nhap xau can giai ma: ");
        String ciphertext = scanner.nextLine();
        StringBuilder decryptedText = new StringBuilder();
        decrypt(ciphertext, key, decryptedText);
        System.out.println("Xau da giai ma: " + decryptedText);
    }
}
