package service.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will read the file and return the lines into objects:
 * Lawn, Mower, Instruction
 */
public class FileParser {

    private final String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This function read the file, collect the data into a list.
     * @return a list contains all the data in the file
     */
    public List<String> getLawnMowersAndInstructions() {
        List<String> result = new ArrayList<>();
        if (filePath != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line = reader.readLine();
                while (line != null) {
                    result.add(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
