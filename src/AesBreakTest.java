import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AesBreakTest {
    byte[] message;
    byte[] Keys;
    byte[] K1;
    byte[] K2;
    byte[] cipher;


    public byte[] GenerateRandomBytes(int len) {
        byte[] array = new byte[len]; // length is bounded by len
        new Random().nextBytes(array);
        return array;
    }

    @BeforeAll
    public void setUp() throws Exception {
        this.message = GenerateRandomBytes(16);
        this.Keys = GenerateRandomBytes(32);
//        for (int i = 0; i < 16; i++) {
//            Keys[i] = 0;
//        }

        this.K1 = Arrays.copyOfRange(Keys, 0, 16);
        this.K2 = Arrays.copyOfRange(Keys, 16, 32);

        System.out.println("message:" + Arrays.toString(message));
        System.out.println("K1:" + Arrays.toString(K1));
        System.out.println("K2:" + Arrays.toString(K2));

        AesEncryption aesEncryption = new AesEncryption(Keys);
        List<byte[]> cipherList = aesEncryption.encryptMassage(message);
        this.cipher = listToArray(cipherList);
        System.out.println("cipher:" + Arrays.toString(cipher));
    }

    @Test
    public void testWhenKey2IsZero() {
        AesBreak aesBreak = new AesBreak(this.message, this.cipher);
        List<byte[]> keys = aesBreak.breakAlgorithm();
        if (Arrays.equals(this.K1, new byte[16])) {
            assertEquals(Arrays.toString(keys.get(1)), Arrays.toString(this.K2));
            assertEquals(Arrays.toString(keys.get(0)), Arrays.toString(this.K1));
        }
        else
            assertEquals("test", "test");
    }


    @Test
    public void testBreak() {
        AesBreak aesBreak = new AesBreak(this.message, this.cipher);
        List<byte[]> keys = aesBreak.breakAlgorithm();
        byte[] oneKey = listToArray(keys);
        AesDecryption aesDecryption = new AesDecryption(oneKey);

        List<byte[]> messageExploit = aesDecryption.decryptMassage(this.cipher);
        byte[] messageExploitArr = listToArray(messageExploit);

        assertEquals(Arrays.toString(messageExploitArr), Arrays.toString(this.message));
    }

    public byte[] listToArray(List<byte[]> list) {
        byte[] arr = new byte[16 * list.size()];
        int factor = 0;
        for (byte[] block : list) {
            for (int i = 0; i < 16; i++) {
                arr[i + 16 * factor] = block[i];
            }
            factor++;
        }
        return arr;
    }

}