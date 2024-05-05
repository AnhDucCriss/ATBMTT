package BTThucHanh;

public class Caesar {
	static String encrypt(String text, int k) {
        StringBuilder result = new StringBuilder();

        //Duyệt từng phần tử của đoạn mã cần mã hóa
        for (int i = 0; i < text.length(); i++) {
        	//Lấy ra kí tự trong đoạn mã tại vị trí i
            char ch = text.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                result.append((char) ((ch + k - 'A') % 26 + 'A'));
            }
            
            else {
                result.append((char) ((ch + k - 'a') % 26 + 'a'));
            }
        }
        return result.toString();
    }
	
	static String decrypt(String text, int k) {
        StringBuilder result = new StringBuilder();

        
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            
            if (Character.isUpperCase(ch)) {
                result.append((char) ((ch - k - 'A') % 26 + 'A'));
            }
            
            else {
                result.append((char) ((ch - k - 'a') % 26 + 'a'));
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "HANOI";
        int k = 4;
        System.out.println("Text: " + text);
        System.out.println("Khóa: " + k);
        System.out.println("Mã hóa: " + encrypt(text, k));
        System.out.println("Giải mã: " + decrypt(encrypt(text, k), k));
    }
}
