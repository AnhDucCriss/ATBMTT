package BTThucHanh;

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

	    public static void main(String[] args) {
	        int p = 3;
	        int q = 7;
	        int n = p * q;
	        int e = 2;
	        int phi = (p - 1) * (q - 1);

	        while (e < phi) {
	            // e must be co-prime to phi and
	            // smaller than phi.
	            if (gcd(e, phi) == 1) {
	                break;
	            } else {
	                e = e + 1;
	            }
	        }

	        // Private key (d stands for decrypt)
	        // choosing d such that it satisfies
	        // d*e = 1 + k * totient

	        int k = 2;
	        int d = (1 + (k * phi)) / e;

	        // Message to be encrypted
	        double msg = 12.0;

	        System.out.println("Message data = " + msg);

	        // Encryption c = (msg ^ e) % n
	        double c = Math.pow(msg, e) % n;
	        System.out.println("Encrypted data = " + c);

	        // Decryption m = (c ^ d) % n
	        double m = Math.pow(c, d) % n;
	        System.out.println("Original Message Sent = " + m);
	    }
}
