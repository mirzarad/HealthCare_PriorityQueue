import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mirza Radoncic and Ritwik Banerjee
 */

public class HealthCenter {
    // File path:
    static final Path TREATMENTS_CACHE = Paths.get("C:\\Users\\mirza\\IdeaProjects\\HealthCare_PriorityQueue\\src\\treatments.csv");

    public static void main(String... args) throws IOException {
        // Read input from CSV file
        List<String>    lines      = Files.readAllLines(TREATMENTS_CACHE, StandardCharsets.UTF_8);
        List<Treatment> treatments = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.trim().split(",");
            treatments.add(new Treatment(parts[0],
                                         parts[1],
                                         Double.parseDouble(parts[2]),
                                         Double.parseDouble(parts[3])));
        }

        // PRICE BASED TREATMENT PRIORITY QUEUE:
        PriorityQueue<Treatment> pq1 = PriorityQueue.fromCollection(treatments,
                                                                    new Treatment.PriceBasedTreatmentComparator());
        System.out.println(pq1.toString());

        // SUCCESS BASED TREATMENT PRIORITY QUEUE:
        PriorityQueue<Treatment> pq2 = PriorityQueue.fromCollection(treatments,
                                                                   new Treatment.SuccessBasedTreatmentComparator());
        System.out.println(pq2.toString());
    }
}
