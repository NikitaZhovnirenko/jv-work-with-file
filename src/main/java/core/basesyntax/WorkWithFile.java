package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFromFile(fromFileName);
        int[] totals = calculateTotals(lines);
        List<String> report = buildReport(totals[0], totals[1]);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    private int[] calculateTotals(List<String> lines) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String line : lines) {
            String[] parts = line.split(",");
            int amount = Integer.parseInt(parts[1]);

            if ("supply".equals(parts[0])) {
                supplyTotal += amount;
            } else if ("buy".equals(parts[0])) {
                buyTotal += amount;
            }
        }

        return new int[]{supplyTotal, buyTotal};
    }

    private List<String> buildReport(int supplyTotal, int buyTotal) {
        int result = supplyTotal - buyTotal;

        List<String> report = new ArrayList<>();
        report.add("supply," + supplyTotal);
        report.add("buy," + buyTotal);
        report.add("result," + result);

        return report;
    }

    private void writeToFile(String fileName, List<String> data) {
        try {
            Files.write(Path.of(fileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing file", e);
        }
    }
}
