package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderUtil {

    public static List<String> getCountriesFromCSV(String filePath) {
        List<String> countries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { // Skip header
                    isFirstLine = false;
                    continue;
                }
                countries.add(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }
}
