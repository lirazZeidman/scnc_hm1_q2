import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AesEncryption {

    private byte[] key1;
    private byte[] key2;

    public AesEncryption(byte[] key) {
        this.key1 = Arrays.copyOfRange(key, 0, key.length / 2);
        this.key2 = Arrays.copyOfRange(key, key.length / 2, key.length);
    }

    public List<byte[]> encryptMassage(byte[] massage) {
        //blocks
        int len = massage.length;
        List<byte[]> blocks = new ArrayList<>();
        for (int i = 0; i <= len - 16; i += 16) {
            blocks.add(Arrays.copyOfRange(massage, i, i + 16));
        }

        List<byte[]> cypher = new ArrayList<>();
        for (byte[] block : blocks) {
            cypher.add(encrypt(block));
        }
        return cypher;
    }

    private byte[] encrypt(byte[] block) {
        byte[] part1 = addRoundKey(swapIndexes(block), this.key1);
//        System.out.println("encrypt:part1: " + new String(part1));
        byte[] part2 = addRoundKey(swapIndexes(part1), this.key2);
//        System.out.println("encrypt:part2: " + new String(part2));

        return part2;
    }

    private byte[] swapIndexes(byte[] block) {

        if (block.length != 16) {
            System.out.println("aesEncryption:swapIndexes- block length NOT 16");
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

    private byte[] addRoundKey(byte[] block, byte[] key) {
        byte[] roundedblock = new byte[16];
        for (int i = 0; i <16 ; i++) {
            roundedblock[i]= (byte) (block[i]^key[i]);
        }
        return roundedblock;

    }


}
