package BTThucHanh;

public class Caesar {
	static String encrypt(String text, int s) {
        StringBuilder result = new StringBuilder();

        // traverse text
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            // Encrypt uppercase characters
            if (Character.isUpperCase(ch)) {
                result.append((char) ((ch + s - 'A') % 26 + 'A'));
            }
            // Encrypt lowercase characters
            else {
                result.append((char) ((ch + s - 'a') % 26 + 'a'));
            }
        }
        return result.toString();
    }

    // Driver code to test the above function
    public static void main(String[] args) {
        String text = "TRUONGDAIHOCCONGNGHIEPHANOI";
        int s = 4;
        System.out.println("Text : " + text);
        System.out.println("Shift : " + s);
        System.out.println("Cipher: " + encrypt(text, s));
    }
}
