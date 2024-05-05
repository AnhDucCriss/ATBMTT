package BTThucHanh;

import java.util.Scanner;

public class RSA {
	  static int gcd(int a, int h) {
	        int temp;
	        while (true) {
	            temp = a % h;
	            if (temp == 0) {
	                return h;
	            }
	            a = h;
	            h = temp;
	        }
	    }

	  static int modInverse(int a, int m) {
	        int r, q, y0 = 0, y1 = 1;
	        int y = 0;
	        while (a > 0) {
	            r = m % a;
	            if (r == 0) break;
	            q = m / a;
	            m = a;
	            a = r;
	            y = y0 - q * y1;
	            y0 = y1;
	            y1 = y;
	        }
	        if (a > 1) return -1;
	        if (y > 0) return y;
	        else return y + 26;
	    }
	  
	    public static void main(String[] args) {
			Scanner s = new Scanner(System.in);
			System.out.println("Nhập hệ số của p và q");
			int p, q;
			System.out.print("p = ");
			p = s.nextInt();
			System.out.print("q = ");
			q = s.nextInt();
	        int n = p * q;
	        int e = 2;
	        int phi = (p - 1) * (q - 1);
	        
	        //chọn e
	        while (e < phi) {
	            if (gcd(e, phi) == 1) {
	                break;
	            } else {
	                e = e + 1;
	            }
	        }

	        //Tính d = e^-1 mod phi
	        int d = modInverse(e, phi);

	        
	        double m = 32;

	        System.out.println("Khóa công khai (" + n+","+e+")");
	        System.out.println("Khóa bí mật (" + n+","+d+")");
	        // Encryption c = (m ^ e) % n
	        double c = Math.pow(m, e) % n;
	        System.out.println("Encrypted data = " + c);

	        // Decryption m = (c ^ d) % n
	        double m1 = Math.pow(c, d) % n;
	        System.out.println("Original Message Sent = " + m1);
	    }
}
