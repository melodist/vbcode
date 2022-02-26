package vbcode;

import utils.BinaryTagData;
import utils.IntegerEntryIdGroup;

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

    public static BinaryTagData vbEncode(IntegerEntryIdGroup integerEntryIdGroup) {
        List<Integer> integerEntryIds = integerEntryIdGroup.getIntegerEntryIds();
        List<Byte> binaryEntryIds = new ArrayList<>();
        int binaryEntrySize = 0;

        for (Integer number : integerEntryIds) {
            List<Byte> binaryGroup = vbEncodeNumber(number);
            binaryEntryIds.addAll(binaryGroup);
            binaryEntrySize += binaryGroup.size();
        }
        return new BinaryTagData(binaryEntryIds, binaryEntrySize);
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
