package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));

            int supplyTotal = 0;
            int buyTotal = 0;

            for (String line : lines) {
                String[] parts = line.split(",");

                String operation = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if ("supply".equals(operation)) {
                    supplyTotal += amount;
                } else if ("buy".equals(operation)) {
                    buyTotal += amount;
                }
            }

            int result = supplyTotal - buyTotal;

            List<String> report = new ArrayList<>();
            report.add("supply," + supplyTotal);
            report.add("buy," + buyTotal);
            report.add("result," + result);

            Files.write(Path.of(toFileName), report);

        } catch (IOException e) {
            throw new RuntimeException("Error processing files", e);
        }
    }
}
