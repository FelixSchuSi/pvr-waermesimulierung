package helper;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class IndexCreatorTest {

    private static Stream<Arguments> indexCreatorTestParameters() {
        return Stream.of(
                //           threadCount, cubeSize, expectedResult
                Arguments.of(5, 10 * 10 * 10, List.of(List.of(0, 200), List.of(201, 400), List.of(401, 600), List.of(601, 800), List.of(801, 999))),
                Arguments.of(2, 10 * 10 * 10, List.of(List.of(0, 500), List.of(501, 999))),
                Arguments.of(3, 10 * 10 * 10, List.of(List.of(0, 333), List.of(334, 666), List.of(667, 999)))
        );
    }

    List<List<Integer>> indexCreator(int threadCount, int cubeSize) {
        int maxIndex = cubeSize - 1;
        int stepSize = (int) Math.ceil((cubeSize - 1.0) / threadCount);
        return IntStream.range(0, threadCount)
                .boxed()
                .map(i -> {
                    int startIndex = i == 0 ? 0 : i * stepSize + 1;
                    int endIndex = Math.min((i + 1) * stepSize, maxIndex);
                    return List.of(startIndex, endIndex);
                })
                .collect(Collectors.toList());
    }

    @ParameterizedTest
    @MethodSource("indexCreatorTestParameters")
    void indexCreatorTest(int threadCount, int cubeSize, List<List<Integer>> expectedResult) {
        List<List<Integer>> receivedResult = indexCreator(threadCount, cubeSize);
        System.out.println("expected:  " + expectedResult);
        System.out.println("reiceived: " + receivedResult);
        assertThat(expectedResult).isEqualTo(receivedResult);
    }
}
