
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        run();
//        if (args[0].equals("–e") && args.length == 1) {
//            System.out.println("to encrypt a massage, use the following syntax:" +
//                    "\n     Java –jar aes.jar -e -k < path-to-key-file > -i <path-to-input-file> -o <path-to-output-file>");
//        } else if (args[0].equals("–d") && args.length == 1) {
//            System.out.println("to decrypt a massage, use the following syntax:" +
//                    "\n     Java –jar aes.jar -d -k < path-to-key-file > -i <path-to-input-file> -o <path-to-output-file>");
//        } else if (args[0].equals("–b") && args.length == 1) { //*********************************************
//            System.out.println("to break the encryption algorithm, use the following syntax:" +
//                    "\n     Java –jar aes.jar -b -m < path-to-massage > -c <path-to-cipher> -o <path-to-output>");
//        } else {
//
//            //Get argument from command
//            String keysPath = args[2];
//            String inputFilePath = args[4];
//            String outputFilePath = args[6];
//
//
//            //Split to list of blocks
//            byte[] plainTextSplitToBlocks = utils.readFileFromPath(inputFilePath);
//            byte[] keysSplitToBlocks = utils.readFileFromPath(keysPath);
//
//            if (args[0].equals("–e")) {
//
//                //initial keys
//                AesEncryption aesEnc = new AesEncryption(keysSplitToBlocks);
////                utils.writeToFileFromPath(outputFilePath, plainTextSplitToBlocks);
//                List<byte[]> cypher = aesEnc.encryptMassage(plainTextSplitToBlocks);
//                utils.writeToFileFromPath(outputFilePath, cypher);
//
//            } else if (args[0].equals("–d")) {
//
//                AesDecryption aesDec = new AesDecryption(keysSplitToBlocks);
////                utils.writeToFileFromPath(outputFilePath, plainTextSplitToBlocks);
//                List<byte[]> plain = aesDec.decryptMassage(plainTextSplitToBlocks);
//                utils.writeToFileFromPath(outputFilePath, plain);
//
//            }
//            if (args[0].equals("–b")) {
//
//                String pathPlain = args[2];
//                String pathCypher = args[4];
//                String pathOutput = args[6];
//
//                byte[] plainText = utils.readFileFromPath(pathPlain);
//                byte[] cipher = utils.readFileFromPath(pathCypher);
//
//                AesBreak aesBreak = new AesBreak();
//                List<byte[]> keys = aesBreak.breakAlgorithm(plainText, cipher);
//                utils.writeToFileFromPath(pathOutput, keys);
//            }
//        }
    }

    public static void run() {
        byte[] content = new byte[16];
        try {
            content = Files.readAllBytes(Paths.get("src/self_testing_files_2021/message_short"));
            System.out.println("content: " + new String(content));
            System.out.println("length: " + content.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("**************************************************************");
        System.out.println("starting to test AES implementations...");

        AesEncryption aesEncryption = new AesEncryption(
//                  new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                new byte[]{16, 85, 51, 16, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}); //32 0
        System.out.println("created AesEncryption object V");

//        swap test - its working :)


//        byte[] b = new byte[16];
//        for (int i = 0; i < 16; i++) {
//            b[i]= (byte) (i+1);
//        }
//
//        for (int i = 0; i < 16; i++) {
//            System.out.print(b[i]+",");
//        }
//
//        System.out.println();
//
//        byte[] x =aesEncryption.swapIndexes(b);
//        for (int i = 0; i < 16; i++) {
//            System.out.print(x[i]+",");
//        }
//
//        System.out.println();
//
//        byte[] con =aesEncryption.swapIndexes(content);
//        System.out.println(new String(con));
//
//        con =aesEncryption.swapIndexes(con);
//        System.out.println(new String(con));

        System.out.println("starting to encrypt massage - content...");
        List<byte[]> cipher_blocks = aesEncryption.encryptMassage(content);

        System.out.println("\ncyphered content:");
        for (byte[] cipher_block : cipher_blocks) {
            System.out.println("index: " + cipher_blocks.indexOf(cipher_block) + ", content: " + new String(cipher_block));

        }
        System.out.println("*******\n\n");


        AesDecryption aesDecryption = new AesDecryption(
//                new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                new byte[]{16, 85, 51, 16, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}); //32 0
        System.out.println("created AesDecryption object V");

        System.out.println("starting to decrypt ciphered blocks - content...");
        List<byte[]> plaintext_blocks = aesDecryption.decryptMassage(cipher_blocks);

        System.out.println("\nplaintext blocks content:");
        for (byte[] plaintext : plaintext_blocks) {
            System.out.println("index: " + plaintext_blocks.indexOf(plaintext) + " ,content: " + new String(plaintext));
        }

        System.out.println("\nbreaking aes algorithm:");
        byte[] cipher = new byte[16 * cipher_blocks.size()];
        int factor = 0;
        for (byte[] block : cipher_blocks) {
            for (int i = 0; i < 16; i++) {
                cipher[factor * i] = block[i];
            }
            factor++;
        }
        System.out.println("cipher is:" + Arrays.toString(cipher));

        AesBreak aesBreak = new AesBreak(content, cipher);
        List<byte[]> keys = aesBreak.breakAlgorithm();
        for (byte[] key :
                keys) {
            System.out.println("key: " + keys.indexOf(key) + ": " + Arrays.toString(key));
        }
        System.out.println();
        System.out.println("hi :)");

    }
}
