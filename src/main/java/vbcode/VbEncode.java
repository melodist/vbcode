package vbcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by melodist
 * Date: 2022-02-19 019
 * Time: 오후 11:39
 *
 *
 *
 */
public class VbEncode {

    public static List<Byte> vbEncode(List<Integer> numbers) {
        List<Byte> result = new ArrayList<>();
        for (Integer number : numbers) {
            result.addAll(vbEncodeNumber(number));
        }
        return result;
    }

    public static List<Byte> vbEncodeNumber(Integer number) {
        Integer n = number;
        Deque<Integer> integers = new LinkedList<>();
        while (true) {
            integers.addFirst(n % 128);
            if (n < 128) { break; }
            n /= 128;
        }
        List<Integer> result = new ArrayList<>(integers);
        int lastIndex = result.size() - 1;
        result.set(lastIndex, result.get(lastIndex) + 128);
        return result.stream()
                .map(Integer::byteValue)
                .collect(Collectors.toList());
    }
}
