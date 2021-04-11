
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class old_main {
    public static void main(String[] args) {

        if ("–e".equals(args[0]) && args.length == 1 )  {
            System.out.println("to encrypt a massage, use the following syntax:" +
                    "\n     Java –jar aes.jar -e -k < path-to-key-file > -i <path-to-input-file> -o <path-to-output-file>");
        } else if (args[0].equals("–d") && args.length == 1) {
            System.out.println("to decrypt a massage, use the following syntax:" +
                    "\n     Java –jar aes.jar -d -k < path-to-key-file > -i <path-to-input-file> -o <path-to-output-file>");
        } else if (args[0].equals("–b") && args.length == 1) { //*********************************************
            System.out.println("to break the encryption algorithm, use the following syntax:" +
                    "\n     Java –jar aes.jar -b -m < path-to-massage > -c <path-to-cipher> -o <path-to-output>");
        } else {
/*
            //Get argument from command
            String keysPath = args[2];
            String inputFilePath = args[4];
            String outputFilePath = args[6];


            //Split to list of blocks
            byte[] plainTextSplitToBlocks = utils.readFileFromPath(inputFilePath);
            byte[] keysSplitToBlocks = utils.readFileFromPath(keysPath);

            if (args[0].equals("–e")) {

                //initial keys
                AesEncryption aesEnc = new AesEncryption(keysSplitToBlocks);
//                utils.writeToFileFromPath(outputFilePath, plainTextSplitToBlocks);
                List<byte[]> cypher = aesEnc.encryptMassage(plainTextSplitToBlocks);
                utils.writeToFileFromPath(outputFilePath, cypher);

            } else if (args[0].equals("–d")) {

                AesDecryption aesDec = new AesDecryption(keysSplitToBlocks);
//                utils.writeToFileFromPath(outputFilePath, plainTextSplitToBlocks);
                List<byte[]> plain = aesDec.decryptMassage(plainTextSplitToBlocks);
                utils.writeToFileFromPath(outputFilePath, plain);

            }
            if (args[0].equals("–b")) {

                String pathPlain = args[2];
                String pathCypher = args[4];
                String pathOutput = args[6];

                byte[] plainText = utils.readFileFromPath(pathPlain);
                byte[] cipher = utils.readFileFromPath(pathCypher);

                AesBreak aesBreak = new AesBreak(plainText, cipher);
                List<byte[]> keys = aesBreak.breakAlgorithm();
                utils.writeToFileFromPath(pathOutput, keys);
            }
        */
        }
    }

    public static void run() {
        byte[] content_massage = utils.readFileFromPath("C:\\Users\\ronro\\IdeaProjects\\scnc_hm1_q2\\src\\self_testing_files_2021\\message_short");
        byte[] key = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        AesEncryption aesEncryption = new AesEncryption(key);

        List<byte[]> cipher_blocks = aesEncryption.encryptMassage(content_massage);

        System.out.println("\ncyphered content:");
        for (byte[] cipher_block : cipher_blocks) {
            System.out.println("index: " + cipher_blocks.indexOf(cipher_block) + ", content: " + new String(cipher_block));
        }
        utils.writeToFileFromPath("C:\\Users\\ronro\\IdeaProjects\\scnc_hm1_q2\\src\\self_testing_files_2021\\message_short_enc", cipher_blocks);

        System.out.println("*******");


        AesDecryption aesDecryption = new AesDecryption(key); //32 0
        byte[] content_cipher = utils.readFileFromPath("C:\\Users\\ronro\\IdeaProjects\\scnc_hm1_q2\\src\\self_testing_files_2021\\message_short_enc");

        List<byte[]> plaintext_blocks = aesDecryption.decryptMassage(content_cipher);

        System.out.println("plaintext blocks content:");
        for (byte[] plaintext : plaintext_blocks) {
            System.out.println("index: " + plaintext_blocks.indexOf(plaintext) + " ,content: " + new String(plaintext));
        }

        System.out.println("\nbreaking aes algorithm:");

        AesBreak aesBreak = new AesBreak(content_massage, content_cipher);
        List<byte[]> keys = aesBreak.breakAlgorithm();
        for (byte[] keyi : keys) {
            System.out.println("key: " + (int) (keys.indexOf(keyi) + 1) + ": " + Arrays.toString(keyi));
        }
        System.out.println();
        System.out.println("hi :)");

    }
}
