package vbcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utils.BinaryEntryIdGroup;
import utils.IntegerEntryIdGroup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-02-19 019
 * Time: 오후 11:53
 */
class VbEncodeTest {

    @ParameterizedTest
    @MethodSource("provideNumber")
    public void vbEncodeNumberTest(int n, List<Byte> expected){
        // when
        List<Byte> actual = VbEncode.vbEncodeNumber(n);

        // then
        System.out.println(actual);
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideNumber() {
        return Stream.of(
                Arguments.of(1, List.of((byte) Integer.parseInt("10000001", 2))),
                Arguments.of(256, List.of((byte) Integer.parseInt("00000010", 2), (byte) Integer.parseInt("10000000", 2)))
        );
    }
    
    @Test
    public void binaryStringToIntegerTest(){
        // given
        String binaryString = "10000001";
        int radix = 2;


        // when
        byte actual = (byte) Integer.parseInt(binaryString, radix);
        byte excepted = -127;
        
        // then
        System.out.println(Integer.toBinaryString(actual & 0xff));
        System.out.println((byte) Integer.parseInt(binaryString, radix));
        assertThat(actual).isEqualTo(excepted);
    }

    @Test
    public void vbEncodeTest(){
        // given
        List<Integer> numbers = List.of(1, 256);
        IntegerEntryIdGroup integerEntryIdGroup = new IntegerEntryIdGroup(numbers);

        // when
        BinaryEntryIdGroup binaryEntryIdGroup = VbEncode.vbEncode(integerEntryIdGroup);
        List<Byte> actual = binaryEntryIdGroup.getBinaryEntryIds();

        // then
        List<Byte> expected = Arrays.asList(
                (byte) Integer.parseInt("10000001", 2),
                (byte) Integer.parseInt("00000010", 2),
                (byte) Integer.parseInt("10000000", 2));

        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(expected);
    }

    
}
