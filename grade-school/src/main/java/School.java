import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class School {
    private final Map<String, Integer> roster = new HashMap<>();

    public void add(String studentName, int grade) {
        roster.put(studentName, grade);
    }

    public List<String> roster() {
        return roster.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()
                        .thenComparing(Map.Entry.comparingByKey()))
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<String> grade(int enrolledInGrade) {
        return roster.entrySet().stream()
                .filter(entry -> entry.getValue() == enrolledInGrade)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }
}
