

public class Main {

    public static void main(String[] args) {
        String input = "hello world";
        System.out.println(capatize(input));
    }
    public static String capatize(String input) {
        StringBuilder res = new StringBuilder();
        String[] strings = input.split(" ");
        for (String s : strings) {
            String temp = s.trim();
            if (temp == null || temp.length() == 0) continue;
            res.append((char)('A' + temp.charAt(0) - 'a'));
            if (temp.length() > 1) {
                res.append(temp.substring(1));
            }
            res.append(" ");
        }
        return res.toString();
    }
}
