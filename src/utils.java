import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class utils {
    public static byte[] readFileFromPath(String path) {
        byte[] content = null;
        try {
            content = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    public static void writeToFileFromPath(String path, List<byte[]> data) {
        try {
            FileWriter myWriter = new FileWriter(path);
            for (byte[] block:data ) {
                myWriter.write(new String(block));
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
