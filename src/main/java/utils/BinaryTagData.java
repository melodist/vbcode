package utils;

/**
 * Created by melodist
 * Date: 2022-02-27 027
 * Time: 오전 12:03
 */
public class BinaryTagData {
    private final byte[] binaryTagName;
    private final byte[] binaryEntryData;

    public BinaryTagData(byte[] binaryTagName, byte[] binaryEntryData) {
        this.binaryTagName = binaryTagName;
        this.binaryEntryData = binaryEntryData;
    }

    public byte[] getBinaryTagName() {
        return binaryTagName;
    }

    public byte[] getBinaryEntryData() {
        return binaryEntryData;
    }
}
