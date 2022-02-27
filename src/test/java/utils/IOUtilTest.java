package utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-02-20 020
 * Time: 오전 11:28
 */
class IOUtilTest {

    @Test
    public void stringInputTest(){
        // given
        String filePath = "./src/test/resources/test.txt";

        // when
        Map<String, IntegerEntryIdGroup> entryIdGroupMap = IOUtil.stringInput(filePath);

        // then
        List<Integer> expected = List.of(1, 1234, 25262);
        assertThat(entryIdGroupMap.get("test"))
                .extracting("entryIds").isEqualTo(expected);
    }

    @Test
    public void byteArrayOutputStreamTest() throws Exception{
        // given
        String filePath = "./src/test/resources/test.dat";

        // when
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        byteArrayOutputStream.write(127);

        // then
        byteArrayOutputStream.writeTo(fileOutputStream);
        byteArrayOutputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void binaryOutputTest(){
        // given
        String filePath = "./src/test/resources/test.dat";

        // when
        Map<String, IntegerEntryIdGroup> testMap = new HashMap<>();
        IntegerEntryIdGroup testGroup = new IntegerEntryIdGroup(List.of(1, 256));
        testMap.put("test", testGroup);

        // then
        IOUtil.binaryOutput(testMap, filePath);
    }


    @Test
    public void binaryInputTest(){
        // given
        String filePath = "./src/test/resources/test.dat";

        // when
        List<BinaryTagData> binaryTagDataList = IOUtil.binaryInput(filePath);

        // then
        assertThat(binaryTagDataList).hasSize(1);
    }

    @Test
    public void decodeDataTest(){
        // given
        byte[] binaryTagName = "test".getBytes(StandardCharsets.UTF_8);
        byte[] binaryEntryId = {-127};
        BinaryTagData binaryTagData = new BinaryTagData(binaryTagName, binaryEntryId);
        List<BinaryTagData> binaryTagDataList = List.of(binaryTagData);

        // when
        Map<String, IntegerEntryIdGroup> dataMap = IOUtil.decodeData(binaryTagDataList);

        // then
        IntegerEntryIdGroup expected = dataMap.get("test");
        assertThat(expected.getIntegerEntryIds()).contains(1);
    }

    @Test
    public void stringOutputTest(){
        // given
        String filePath = "./src/test/resources/stringOutputTest.txt";
        byte[] binaryTagName1 = "test1".getBytes(StandardCharsets.UTF_8);
        byte[] binaryEntryId1 = {-127};
        byte[] binaryTagName2 = "test2".getBytes(StandardCharsets.UTF_8);
        byte[] binaryEntryId2 = {-128, 1, -128};
        BinaryTagData binaryTagData1 = new BinaryTagData(binaryTagName1, binaryEntryId1);
        BinaryTagData binaryTagData2 = new BinaryTagData(binaryTagName2, binaryEntryId2);
        List<BinaryTagData> binaryTagDataList = List.of(binaryTagData1, binaryTagData2);

        // when
        IOUtil.stringOutput(binaryTagDataList, filePath);
    }
}
