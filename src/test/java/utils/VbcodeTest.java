package utils;

import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * Created by melodist
 * Date: 2022-02-21 021
 * Time: 오후 11:59
 */
public class VbcodeTest {

    @Test
    public void binaryOutputTest(){
        // given
        String inputPath = "./src/test/resources/test.txt";
        String outputPath = "./src/test/resources/test.dat";

        // when
        Map<String, IntegerEntryIdGroup> normalData = IOUtil.stringInput(inputPath);
        IOUtil.binaryOutput(normalData, outputPath);

        // then
    }
}
