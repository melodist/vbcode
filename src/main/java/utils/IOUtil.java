package utils;

import vbcode.VbEncode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                BinaryTagData binaryTagData = VbEncode.vbEncode(integerEntryIdGroup);

                // Write Binary Data
                // 1. Tag Data
                byte[] tagNameBytes = tagName.getBytes(StandardCharsets.UTF_8);
                byteOut.write(tagNameBytes.length);
                byteOut.write(tagNameBytes);

                // 2. EntryIdGroup Data
                List<BinaryGroup> binaryEntryIds = binaryTagData.getBinaryEntryIds();
                List<Integer> binaryEntrySizes = binaryTagData.getBinaryEntrySizes();
                // write byteEntry
                IntStream
                        .range(0, binaryEntryIds.size())
                        .forEach(i -> {
                            byteOut.write(binaryEntrySizes.get(i));
                            binaryEntryIds.get(i).getBinaryEntryIds()
                                    .forEach(byteOut::write);
                        });
            }

            byteOut.writeTo(out);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
