import java.util.List;
import java.util.stream.Stream;

record KindergartenGarden(String garden) {

    List<Plant> getPlantsOfStudent(String student) {
        var index = 2 * (student.charAt(0) - 'A');
        return garden.lines()
                .map(line -> line.subSequence(index, index + 2))
                .flatMap(s -> Stream.of(s.charAt(0), s.charAt(1)))
                .map(Plant::getPlant)
                .toList();
    }
}
