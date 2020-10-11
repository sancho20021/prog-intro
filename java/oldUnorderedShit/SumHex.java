public class SumHex {
    static int getNumber(String s) {
        if (s.startsWith("0x") || s.startsWith("0X")) {
            return Integer.parseUnsignedInt(s.substring(2), 16);
        } else {
            return Integer.parseInt(s);
        }
    }

    public static void main(String[] args) {
        int res = 0;
        StringBuilder s = new StringBuilder();
        for (String x : args) {
            boolean isNumber = false;
            s.setLength(0);
            for (int i = 0; i < x.length() + 1; i++) {
                char c = i == x.length() ? ' ' : x.charAt(i);
                if (!Character.isWhitespace(c)) {
                    s.append(c);
                    isNumber = true;
                } else if (isNumber) {
                    res += getNumber(s.toString());
                    s.setLength(0);
                    isNumber = false;
                }
            }
        }
        System.out.println(res);
    }
}
