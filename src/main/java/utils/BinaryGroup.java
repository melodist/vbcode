package utils;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-21 021
 * Time: 오전 12:43
 */
public class BinaryGroup {
    private final List<Byte> binaryEntryIds;

    public BinaryGroup(List<Byte> byteEntryIds) {
        this.binaryEntryIds = byteEntryIds;
    }

    public List<Byte> getBinaryEntryIds() {
        return binaryEntryIds;
    }

    public int size() {
        return binaryEntryIds.size();
    }
}
