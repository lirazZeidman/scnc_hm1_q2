import org.junit.jupiter.api.BeforeEach;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AesBreakTest {
    private AesBreak aesBreak;

    public byte[] GenerateRandomBytes() {
        byte[] array = new byte[48]; // length is bounded by 48
        new Random().nextBytes(array);
        return array;
    }

    @BeforeEach
    public void setUp() throws Exception {
        byte[] message = GenerateRandomBytes();
//        aesBreak = new AesBreak(message,)
    }
}