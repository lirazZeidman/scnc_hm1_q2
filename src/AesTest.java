import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;


public class AesTest {

    private AesEncryption aesEncryption;

    public byte[] GenerateRandomBytes(int len) {
        byte[] array = new byte[len]; // length is bounded by 48
        new Random().nextBytes(array);
        return array;
    }

    @BeforeEach
    public void setUp() throws Exception {
        byte[] keys = GenerateRandomBytes(32);
        aesEncryption = new AesEncryption(keys);
    }

    @Test
    public void testEncryption() throws Exception {
        int n_blocks = new Random().nextInt(10);
        byte[] massage = GenerateRandomBytes(n_blocks * 16);

    }
}

