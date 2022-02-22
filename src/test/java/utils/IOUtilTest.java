package utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
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

}
