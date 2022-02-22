package vbcode;

import utils.BinaryGroup;
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
        List<BinaryGroup> binaryEntryIds = new ArrayList<>();
        List<Integer> binaryEntrySizes = new ArrayList<>();

        for (Integer number : integerEntryIds) {
            BinaryGroup binaryGroup = vbEncodeNumber(number);
            binaryEntryIds.add(binaryGroup);
            binaryEntrySizes.add(binaryGroup.size());
        }
        return new BinaryTagData(binaryEntryIds, binaryEntrySizes);
    }

    public static BinaryGroup vbEncodeNumber(Integer number) {
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
        return new BinaryGroup(
                result.stream()
                .map(Integer::byteValue)
                .collect(Collectors.toList()));
    }
}
