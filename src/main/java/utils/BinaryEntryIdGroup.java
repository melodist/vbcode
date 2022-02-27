package utils;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-21 021
 * Time: 오후 11:11
 */
public class BinaryEntryIdGroup {
    private final List<Byte> binaryEntryIds;
    private final int binaryEntrySize;

    public BinaryEntryIdGroup(List<Byte> binaryEntryIds, int binaryEntrySize) {
        this.binaryEntryIds = binaryEntryIds;
        this.binaryEntrySize = binaryEntrySize;
    }

    public List<Byte> getBinaryEntryIds() {
        return binaryEntryIds;
    }

    public int getBinaryEntrySize() {
        return binaryEntrySize;
    }
}
