import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AesBreak {
    private byte[] message;
    private byte[] cipher;

    public AesBreak(byte[] message, byte[] cipher) {
        this.message = message;
        this.cipher = cipher;
    }

    public List<byte[]> breakAlgorithm() {

        byte[] mXorC = getXor(this.message, this.cipher);

        //K1T will be initialized to {0,0,...,0} () - which means K1 also is {0,0,...,0}
        byte[] K1T = new byte[16];

        List<byte[]> mXorCXorK1T = new ArrayList<>();

        for (int i = 0; i <= mXorC.length - 16; i += 16) {
            mXorCXorK1T.add(getXor(Arrays.copyOfRange(mXorC, i, i + 16), K1T));
        }

        //Xor is linear function. Hence, 1 pair of k1,k2 satisfies our need. therefore we will find the key-pair of the first block.
        byte[] K2 = mXorCXorK1T.get(0);

        List<byte[]> keys = new ArrayList<>();
        keys.add(K1T);
        keys.add(K2);

        return keys;
    }

    private byte[] getXor(byte[] byteArr1, byte[] byteArr2) {
        byte[] Xor = new byte[byteArr1.length];
        for (int i = 0; i < byteArr1.length; i++) {
            Xor[i] = (byte) (byteArr1[i] ^ byteArr2[i]);
        }
        return Xor;
    }

    private byte[] swapIndexes(byte[] block) {

        if (block.length != 16) {
            System.out.println("AesBreak.java : swapIndexes - block length NOT 16");
        }

        byte[][] twoD_block = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                twoD_block[j][i] = block[4 * i + j];
            }
        }

        byte[][] swaped_2Dblock = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                swaped_2Dblock[i][j] = twoD_block[j][i];
            }
        }

        byte[] swaped_block = new byte[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                swaped_block[4 * i + j] = swaped_2Dblock[j][i];
            }

        }
        return swaped_block;
    }

}
