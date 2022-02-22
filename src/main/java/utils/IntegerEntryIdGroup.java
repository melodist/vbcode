package utils;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-20 020
 * Time: 오전 11:31
 */
public class IntegerEntryIdGroup {
    private final List<Integer> integerEntryIds;

    public IntegerEntryIdGroup(List<Integer> entryIds) {
        this.integerEntryIds = entryIds;
    }

    public List<Integer> getIntegerEntryIds() {
        return integerEntryIds;
    }
}
