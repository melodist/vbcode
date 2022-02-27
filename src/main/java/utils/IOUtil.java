package utils;

import vbcode.VbDecode;
import vbcode.VbEncode;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by melodist
 * Date: 2022-02-20 020
 * Time: 오전 10:51
 */
public class IOUtil {

    public static Map<String, IntegerEntryIdGroup> stringInput(String filePath) {
        Map<String, IntegerEntryIdGroup> result = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] spliced = line.split(" ");
                String tagName = spliced[0];
                IntegerEntryIdGroup entryIdGroup = createEntryIdGroup(spliced[1]);

                result.put(tagName, entryIdGroup);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

    private static IntegerEntryIdGroup createEntryIdGroup(String entryIdString) {
        return new IntegerEntryIdGroup( Arrays.stream(entryIdString.split(","))
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList()));
    }

    public static void binaryOutput(Map<String, IntegerEntryIdGroup> dataMap, String filePath) {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

            for (String tagName : dataMap.keySet()) {
                // Encode Data
                IntegerEntryIdGroup integerEntryIdGroup = dataMap.get(tagName);
                BinaryEntryIdGroup binaryEntryIdGroup = VbEncode.vbEncode(integerEntryIdGroup);

                // Write Binary Data
                // 1. Tag Data
                byte[] tagNameBytes = tagName.getBytes(StandardCharsets.UTF_8);
                byteOut.write(tagNameBytes.length);
                byteOut.write(tagNameBytes);

                // 2. EntryIdGroup Data
                List<Byte> binaryEntryIds = binaryEntryIdGroup.getBinaryEntryIds();
                int binaryEntrySize = binaryEntryIdGroup.getBinaryEntrySize();

                // write byteEntrySize
                byteOut.write(binaryEntrySize);
                // write byteEntry
                int bound = binaryEntryIds.size();
                for (Byte binaryEntryId : binaryEntryIds) {
                    byteOut.write(binaryEntryId.intValue());
                }
            }

            byteOut.writeTo(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<BinaryTagData> binaryInput(String filePath) {
        List<BinaryTagData> binaryTagDataList = new ArrayList<>();

        // read binary file
        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] integerBuffer = new byte[4];
            int offset = 0;
            int INTEGER_SIZE = 4;

            while ((fileInputStream.readNBytes(integerBuffer, offset, INTEGER_SIZE)) == 4) {
                // read tag length
                int tagSize = ByteBuffer.wrap(integerBuffer).getInt();

                // read tag
                byte[] binaryTagName = fileInputStream.readNBytes(tagSize);

                // read binary entry length
                fileInputStream.readNBytes(integerBuffer, offset, INTEGER_SIZE);
                int binaryEntrySize = ByteBuffer.wrap(integerBuffer).getInt();

                // read binary entry
                byte[] binaryEntry = fileInputStream.readNBytes(binaryEntrySize);

                binaryTagDataList.add(new BinaryTagData(binaryTagName, binaryEntry));
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return binaryTagDataList;
    }

    public static void stringOutput(List<BinaryTagData> binaryTagDataList, String filePath) {
        Map<String, IntegerEntryIdGroup> dataMap = decodeData(binaryTagDataList);

        // write string data
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            for (String tagName : dataMap.keySet()) {
                String entryIdString = dataMap.get(tagName).getIntegerEntryIds()
                        .stream().map(Object::toString).collect(Collectors.joining(","));

                out.write(tagName.getBytes(StandardCharsets.UTF_8));
                out.write(" ".getBytes(StandardCharsets.UTF_8));
                out.write(entryIdString.getBytes(StandardCharsets.UTF_8));
                out.write("\n".getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Map<String, IntegerEntryIdGroup> decodeData(List<BinaryTagData> binaryTagDataList) {
        // decode data
        Map<String, IntegerEntryIdGroup> dataMap = new HashMap<>();
        for (BinaryTagData binaryTagData: binaryTagDataList) {
            byte[] binaryEntryData = binaryTagData.getBinaryEntryData();
            String tagName = new String(binaryTagData.getBinaryTagName());
            List<Integer> entryIds = VbDecode.vbDecode(binaryEntryData);
            IntegerEntryIdGroup integerEntryIdGroup = new IntegerEntryIdGroup(entryIds);
            dataMap.put(tagName, integerEntryIdGroup);
        }
        return dataMap;
    }
}
