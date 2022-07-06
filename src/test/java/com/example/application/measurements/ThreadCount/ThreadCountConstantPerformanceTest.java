package com.example.application.measurements.ThreadCount;

public class ThreadCountConstantPerformanceTest {
//
//    private final Map<String, BaseConfigEntity> testCases = ThreadCountTestCasesConstant.all();
//    private final int TEST_RERUN_COUNT = 100;
//    private final SimulationServiceFromConfigService serviceFromConfig = new SimulationServiceFromConfigService();
//
//    @Test
//    @Disabled
//    void runAll() {
//        List<String> numberStrings = IntStream.range(0, TEST_RERUN_COUNT).boxed().map(Object::toString).collect(Collectors.toList());
//        List<String> columns = new ArrayList<>(List.of("runName", "threadCount", "implementationStrategy"));
//        columns.addAll(numberStrings);
//        CsvReport report = new CsvReport(columns.stream());
//        int totalRunCount = testCases.size() * TEST_RERUN_COUNT;
//        AtomicInteger currentRunCount = new AtomicInteger(1);
//        testCases.forEach((testRunName, config) -> {
//            List<String> row = new ArrayList<>(List.of(testRunName, config.getThreadCount().toString(), config.getImplementationEnum().getImplementation()));
//            for (int i = 0; i < TEST_RERUN_COUNT; i++) {
//                BaseSimulationService implementation;
//                try {
//                    implementation = serviceFromConfig.apply(config);
//                } catch (NotImplementedException e) {
//                    System.out.println(e);
//                    continue;
//                }
//                System.out.println(currentRunCount + "/" + totalRunCount + " " + testRunName + " rerun " + i);
//                currentRunCount.getAndIncrement();
//                long t0 = System.nanoTime();
//                for (int step = 0; step < config.getStepCount(); step++) {
//                    implementation.next();
//                }
//
//                long t1 = System.nanoTime();
//                row.add(Long.toString((t1 - t0) / 1000000));
//            }
//            report.appendRow(row.stream());
//        });
//        try {
//            report.writeFile("thread_count_constant.csv");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
