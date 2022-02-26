package utils;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-21 021
 * Time: 오후 11:11
 */
public class BinaryTagData {
    private List<Byte> binaryEntryIds;
    private int binaryEntrySize;

    public BinaryTagData(List<Byte> binaryEntryIds, int binaryEntrySize) {
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
