package vbcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-20 020
 * Time: 오전 9:41
 */
public class VbDecode {

    public static List<Integer> vbDecode(byte[] encodedList) {
        int n = 0;
        List<Integer> decoded = new ArrayList<>();
        for (byte encoded : encodedList) {
            if (encoded >= 0) {
                n = 128 * n + encoded;
            } else {
                n = 128 * n + encoded + 128;
                decoded.add(n);
                n = 0;
            }
        }
        return decoded;
    }
}
