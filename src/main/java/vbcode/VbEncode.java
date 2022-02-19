package vbcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-19 019
 * Time: 오후 11:39
 *
 * Pseudocode
 *
 */
public class VbEncode {

    public static List<Integer> vbEncode(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            result.add(vbEncodeNumber(number));
        }
        return null;
    }

    private static Integer vbEncodeNumber(Integer number) {
        Deque<Byte> result = new LinkedList<>();
        while (true) {
            result.addFirst(Byte.parseByte(String.valueOf(number % 128)));
        }
    }
}
