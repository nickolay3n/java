import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class JarValidator {

    public static void main(String[] args) throws IOException {
        //Path repositoryPath = Paths.get("C:\\Users\\User\\.m2");
        Path repositoryPath = Paths.get("D:\\server\\maven\\repository");


        // Check if the main Repository Exists
        if (Files.exists(repositoryPath)) {

            // Create a class instance
            JarValidator jv = new JarValidator();

            List<String> jarReport = new ArrayList<>();
            jarReport.add("Repository to process: " + repositoryPath.toString());

            // Get all the directory files
            List<Path> jarFiles = jv.getFiles(repositoryPath, ".jar");
            jarReport.add("Number of jars to process: " + jarFiles.size());
            jarReport.addAll(jv.openJars(jarFiles, true));

            // Print the report
            jarReport.stream().forEach(System.out::println);

        } else {
            System.out.println("Repository path " + repositoryPath + " does not exist.");
        }
    }

    /**
     * Get all the files from the given directory matching the specified extension
     *
     * @param filePath      Absolute File Path
     * @param fileExtension File extension
     * @return A list of all the files contained in the directory
     * @throws IOException
     */
    private List<Path> getFiles(Path filePath, String fileExtension) throws IOException {
        return Files.walk(filePath).filter(p -> p.toString().endsWith(fileExtension)).collect(Collectors.toList());
    }

    /**
     * Try to open all the jar files
     *
     * @param jarFiles
     * @return A List of Messages for Corrupted Jars
     */
    private List<String> openJars(List<Path> jarFiles, boolean showOkayJars) {
        int[] badJars = { 0 };
        List<String> messages = new ArrayList<>();

        // For Each Jar
        jarFiles.forEach(path -> {

            try (JarFile file = new JarFile(path.toFile())) {
                if (showOkayJars) {
                    //messages.add("OK : " + path.toString());
                    //закоментил значит все хорошо
                }
            } catch (IOException ex) {
                messages.add(path.toAbsolutePath() + " threw exception: " + ex.toString());
                badJars[0]++;
                //удаляем плохие
                try {
                    Files.delete(Path.of(String.valueOf(path.toAbsolutePath())));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        messages.add("Total bad jars = " + badJars[0]);
        return messages;
    }

}