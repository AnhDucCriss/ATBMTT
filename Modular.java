package BTThucHanh;

import java.util.Scanner;

public class Modular {
	public static int calcExponent(int a, int b, int n) {
		String binaryB = Integer.toBinaryString(b);//khai báo biến để tách b về dạng nhị phân
		int f=1;
		for(int i=0;i< binaryB.length();i++) {
			f = (f*f) % n;
			if(binaryB.charAt(i) == '1') {
				f = (f*a) % n;
			}
			
		}
		return f;
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a,b,n;
		System.out.println("Nhap lan luot a, b, n cua phep tinh a^b mod n: ");
		a = s.nextInt();
		b = s.nextInt();
		n = s.nextInt();
		System.out.println("Ket qua cua phep tinh ("+a+"^"+b+") mod "+n+" = "+calcExponent(a, b, n));
		
	}
}
