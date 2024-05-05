package BTThucHanh;

import java.util.Scanner;

public class Hill {
	/*
	 * Phương thức tính nghịch đảo của một số a trong modulo n sử dụng thuật toán Euclid mở rộng.
	 */
	static int inverse(int a, int m) {
        int r, q, x0 = 0, x1 = 1;
        int x = 0;
        while (a > 0) {
            r = m % a;
            if (r == 0) break;
            q = m / a;
            m = a;
            a = r;
            x = x0 - q * x1;
            x0 = x1;
            x1 = x;
        }
        if (a > 1) return -1;
        if (x > 0) return x;
        else return x + 26;
    }
	
	/*
	 * Phương thức nhận vào một vector và ma trận key, thực hiện phép nhân giữa chúng và trả về kết quả.
	 */
    static void multiplyMatrix(int[] input, int[][] key, int[] result) {
        for (int i = 0; i < 2; i++) {
            int sum = 0;
            for (int j = 0; j < 2; j++) {
                sum += input[j] * key[j][i];
            }
            result[i] = sum % 26;
        }
    }
    
    /*
     * Phương thức mã hóa văn bản text bằng ma trận key và kết quả được lưu vào StringBuilder result.
     */
    static void encrypt(String text, int[][] key, StringBuilder result) {
        int length = text.length();
        for (int i = 0; i < length; i += 2) {
            int[] kq = new int[2];
            int[] vector = new int[2];
            for (int j = 0; j < 2; j++) {
                vector[j] = text.charAt(i + j) - 'A';
            }
            multiplyMatrix(vector, key, kq);

            for (int j = 0; j < 2; j++) {
                result.append((char) (kq[j] + 'A'));
            }
        }
    }

    /*
     * Phương thức tính ma trận nghịch đảo của key.
     */
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

    /*
     * Phương thức giải mã văn bản ciphertext bằng ma trận key và kết quả được lưu vào StringBuilder result.
     */
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

        System.out.println("Nhập khóa k");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print("k["+(i+1)+"]["+(j+1)+"] : ");
                key[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine(); // consume newline character
        System.out.print("Nhập xâu cần mã hóa: ");
        String plaintext = scanner.nextLine();
        StringBuilder encryptedText = new StringBuilder();
        encrypt(plaintext, key, encryptedText);
        System.out.println("Xâu sau khi mã hóa: " + encryptedText);

        System.out.print("Nhập xâu cần giải mã: ");
        String ciphertext = scanner.nextLine();
        StringBuilder decryptedText = new StringBuilder();
        decrypt(ciphertext, key, decryptedText);
        System.out.println("Xâu đã giải mã: " + decryptedText);
    }
}
