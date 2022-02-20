package vbcode;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by melodist
 * Date: 2022-02-20 020
 * Time: 오전 10:05
 */
class VbDecodeTest {

    @Test
    public void vbDecodeTest(){
        // given
        List<Byte> bytesFor1 = List.of((byte) Integer.parseInt("10000001", 2));
        List<Byte> bytesFor256 = List.of((byte) Integer.parseInt("00000010", 2), (byte) Integer.parseInt("10000000", 2));
        List<Byte> encoded = Stream.of(bytesFor1, bytesFor256)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // when
        List<Integer> actual = VbDecode.vbDecode(encoded);

        // then
        List<Integer> expected = List.of(1, 256);
        assertThat(actual).isEqualTo(expected);
    }
}
