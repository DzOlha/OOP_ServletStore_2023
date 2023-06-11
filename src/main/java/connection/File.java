package connection;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

@Getter
public class File {
    private final String filePath = "D:\\Мої документи\\ПРЕДМЕТИ\\3 course\\II sem\\ООП\\Store\\src\\main\\resources\\db_credentials.txt";
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    public File() {
        // Create a Path object for the file
        Path path = Paths.get(filePath);

        try {
            // Read the file using a Scanner
            Scanner scanner = new Scanner(path);

            // Read the first line
            dbUrl = scanner.nextLine();

            // Read the second line
            dbUsername = scanner.nextLine();

            // Read the third line
            dbPassword = scanner.nextLine();

            // Close the Scanner
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
