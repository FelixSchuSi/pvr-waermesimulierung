package helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CsvReport {
    private Stream<Stream<String>> dataLines;

    public CsvReport(Stream<String> columnNames) {
        dataLines = Stream.of(columnNames);
    }

    public void appendRow(Stream<String> row) {
        dataLines = Stream.concat(dataLines, Stream.of(row));
    }

    public void writeFile(String filename) throws IOException {
        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.map(this::convertToCSV)
                    .forEach(pw::println);
        }
        assertTrue(csvOutputFile.exists());
    }

    private String convertToCSV(Stream<String> data) {
        return data.map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}
