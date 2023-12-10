package org.example.util;

/**
 * scale convert tool
 */
public class ScaleConvertUtils {

    private static final char[] arr = {'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f', 'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K',
      'l', 'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r', 'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x', 'X', 'y', 'Y', 'z',
      'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',};

    public static String _10_to_62(long num) {
        var builder = new StringBuilder();
        while (num != 0) {
            var pos = num - (num / 62) * 62;
            builder.append(arr[(int) pos]);
            num = num / 62;
        }
        return builder.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(_10_to_62(1000000));
    }

}
