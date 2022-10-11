package service.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will read the file and return a string.
 */
public class FileParser {
    private final Logger logger = LoggerFactory.getLogger(FileParser.class);
    private final String filePath;

    public FileParser(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This function reads the file, collect the data into a list.
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
                logger.error("Error in reading file " + filePath);
                e.printStackTrace();
            }
        }
        return result;
    }

}
