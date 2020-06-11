import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static String pathToFile = "C:\\Users\\paulh\\Downloads\\2020-06-09_17-42-26.log";
    private static String outFile = "C:\\Users\\paulh\\Downloads\\clean.log";

    public static void main(String[] args){


        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            PrintWriter writer = new PrintWriter(outFile, "UTF-8");
            String line;

            Path path = Paths.get(pathToFile);
            long lineCount = Files.lines(path).count();
            long c = 1;

            while ((line = br.readLine()) != null) {

                char[] arr = line.toCharArray();
                if(arr[0] == ' ' || arr[0] == '-' || line.contains("WARNING") || line.contains("TextLog")
                        || line.contains("VERBOSE") || line.contains("<CheckForUpdatesAsync>") ||
                line.contains("Saving world...")) {
                    System.out.println("[" + c + "/" + lineCount + "] Ignoring line");
                } else {
                    System.out.println("[" + c + "/" + lineCount + "] Printing line");
                    writer.println(line);
                }
                c++;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
