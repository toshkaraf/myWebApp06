import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * GKislin
 * 23.10.2015.
 */
public class MainFiles {
    public static void main(String[] args) {
        System.out.println("\nBy BufferedReader\n");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("./log/webapp.log.0.1"), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Warning " + e.getMessage());
                }
            }
        }
    }
}
