package vbcode;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        byte[] encoded = {(byte) Integer.parseInt("10000001", 2),
                (byte) Integer.parseInt("00000010", 2),
                (byte) Integer.parseInt("10000000", 2)};

        // when
        List<Integer> actual = VbDecode.vbDecode(encoded);

        // then
        List<Integer> expected = List.of(1, 256);
        assertThat(actual).isEqualTo(expected);
    }
}
