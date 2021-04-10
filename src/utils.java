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

    public static void writeToFileFromPath(String path, List<byte[]> data) { //***********change
        try {
           byte[] content = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
