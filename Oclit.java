package BTThucHanh;

import java.util.Scanner;

public class Oclit {
	static int modInverse(int a, int m) {
		
        int r, q, y0 = 0, y1 = 1;
        int y = 0;
        while (a > 0) {
            r = m % a;
            q = m / a;
            if (r == 0) break;
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
	
  
    public static void main(String args[])
    {
    	Scanner s = new Scanner(System.in);
		System.out.println("Nhập hệ số của a và m");
		int a, m;
		System.out.print("a = ");
		a = s.nextInt();
		System.out.print("m = ");
		m = s.nextInt();
        System.out.println("Số nghịch đảo của phép nhân mô-đun ("+a+"^-1 mod "+m+ ") là: " +modInverse(a, m));
    }
}
