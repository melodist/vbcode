package utils;

import java.util.List;

/**
 * Created by melodist
 * Date: 2022-02-21 021
 * Time: 오후 11:11
 */
public class BinaryTagData {
    private List<BinaryGroup> binaryEntryIds;
    private List<Integer> binaryEntrySizes;

    public BinaryTagData(List<BinaryGroup> binaryEntryIds, List<Integer> binaryEntrySizes) {
        this.binaryEntryIds = binaryEntryIds;
        this.binaryEntrySizes = binaryEntrySizes;
    }

    public List<BinaryGroup> getBinaryEntryIds() {
        return binaryEntryIds;
    }

    public List<Integer> getBinaryEntrySizes() {
        return binaryEntrySizes;
    }
}
