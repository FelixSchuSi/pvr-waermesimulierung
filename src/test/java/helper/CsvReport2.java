package helper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReport2 {
    private final Map<String, List<String>> tableData = new HashMap<>();

    public void appendData(String column, String data) {
        if (tableData.containsKey(column)) {
            tableData.get(column).add(data);
        } else {
            tableData.put(column, List.of(data));
        }
    }

    public void writeFile(String filename) throws IOException {
        List<List<String>> data = new ArrayList<>(List.of(new ArrayList<>(tableData.keySet())));
        int maxLength = tableData.values().stream().map(List::size).max(Integer::compareTo).orElse(0);

        for (int i = 0; i < maxLength; i++) {
            data.add(List.of());
            for (Map.Entry<String, List<String>> entry : tableData.entrySet()) {
                int columnId = data.get(0).indexOf(entry.getKey());
                data.get(i + 1).set(columnId, entry.getValue().get(i));
            }
        }


        File csvOutputFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println("sep=,");
            data.stream()
                    .map(Collection::stream)
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
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
