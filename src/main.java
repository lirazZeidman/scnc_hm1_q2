import java.util.List;

public class main {
    public static void main(String[] args) {
        if (args[0].equals("-e") && args.length == 1) {
            System.out.println("to encrypt a massage, use the following syntax:" +
                    "\n     Java –jar aes.jar -e -k < path-to-key-file > -i <path-to-input-file> -o <path-to-output-file>");
        } else if (args[0].equals("-d") && args.length == 1) {
            System.out.println("to decrypt a massage, use the following syntax:" +
                    "\n     Java –jar aes.jar -d -k < path-to-key-file > -i <path-to-input-file> -o <path-to-output-file>");
        } else if (args[0].equals("-b") && args.length == 1) {
            System.out.println("to break the encryption algorithm, use the following syntax:" +
                    "\n     Java –jar aes.jar -b -m < path-to-massage > -c <path-to-cipher> -o <path-to-output>");
        } else {
            String keysPath = args[2];
            String inputFilePath = args[4];
            String outputFilePath = args[6];

            byte[] plain_text = utils.readFileFromPath(inputFilePath);
            byte[] keys       = utils.readFileFromPath(keysPath);

            if (args[0].equals("-e")) {

                //initial keys
                AesEncryption aesEnc = new AesEncryption(keys);
                List<byte[]> cypher = aesEnc.encryptMassage(plain_text);
                utils.writeToFileFromPath(outputFilePath, cypher);

            } else if (args[0].equals("-d")) {

                AesDecryption aesDec = new AesDecryption(keys);
                List<byte[]> plain = aesDec.decryptMassage(plain_text);
                utils.writeToFileFromPath(outputFilePath, plain);

            }
            else if (args[0].equals("-b")) {

                String pathPlain = args[2];
                String pathCypher = args[4];
                String pathOutput = args[6];

                byte[] plainText = utils.readFileFromPath(pathPlain);
                byte[] cipher = utils.readFileFromPath(pathCypher);

                AesBreak aesBreak = new AesBreak(plainText, cipher);
                List<byte[]> calcKeys = aesBreak.breakAlgorithm();
                utils.writeToFileFromPath(pathOutput, calcKeys);
            }


        }

        System.out.println("\nHi :)");
    }
}
