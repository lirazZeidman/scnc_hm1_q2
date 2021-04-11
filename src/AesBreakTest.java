import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AesBreakTest {
    private AesBreak aesBreak;

    public byte[] GenerateRandomBytes(int len) {
        byte[] array = new byte[len]; // length is bounded by len
        new Random().nextBytes(array);
        return array;
    }

    @BeforeEach
    public void setUp() throws Exception {
        byte[] message = GenerateRandomBytes(48);
        byte[] Keys = GenerateRandomBytes(32);
        for (int i = 0; i < 16; i++) {
            Keys[i] = 0;
        }

        byte[] K1 = Arrays.copyOfRange(Keys, 0, 16);
        byte[] K2 = Arrays.copyOfRange(Keys, 16, 32);

        System.out.println("message:" + Arrays.toString(message));
        System.out.println("K1:" + Arrays.toString(K1));
        System.out.println("K2:" + Arrays.toString(K2));

        AesEncryption aesEncryption = new AesEncryption(Keys);
        List<byte[]> cipher = aesEncryption.encryptMassage(message);
        System.out.print("cipher: ");
        for (byte[] block : cipher) {
            System.out.print(Arrays.toString(block));
        }
    }

    @Test
    public void testMultiply() {
        assertEquals(20, 4 * 5);
    }

}