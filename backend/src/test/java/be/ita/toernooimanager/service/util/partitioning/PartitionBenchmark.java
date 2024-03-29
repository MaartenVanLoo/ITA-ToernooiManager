package be.ita.toernooimanager.service.util.partitioning;


import be.ita.toernooimanager.service.util.partitioning.Exceptions.AlgorithmUnknownException;
import be.ita.toernooimanager.service.util.partitioning.Exceptions.NoBinsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class PartitionBenchmark {
    final long maxCallCount = (long) 1e6;
    long seed = 0;
    @BeforeEach
    void setUp() {
        this.seed = 0x14654684;
    }

    @Test
    public void benchmark() throws AlgorithmUnknownException, NoBinsException {
        MessageFormat greedyMessage = new MessageFormat("Greedy:\tBins:{0}\tSamples:{1}.");
        MessageFormat dsTreeMessage = new MessageFormat("DSTree:\tBins:{0}\tSamples:{1}.");
        MessageFormat isTreeMessage = new MessageFormat("ISTree:\tBins:{0}\tSamples:{1}.");

        int[] binCounts = new int[]{2,4,6,8,12};
        //int[] binCounts = new int[]{50};
        int[] sampleCounts = new int[]{5,10,15,30,50,100};
        //int[] sampleCounts = new int[]{1000};
        int minVal = 3;
        int maxVal = 100;

        List<List<String>> rows = new ArrayList<>();
        List<String> headers = Arrays.asList("bins","samples","|","","Greedy","","|","","DsTree","","|","","IsTree","");
        List<String> subheaders = Arrays.asList("","","|","Range","Time","Calls","|","Range","Time","Calls","|","Range","Time","Calls");
        rows.add(headers);
        rows.add(subheaders);

        Random random= new Random(seed);
        for (int sampleCount: sampleCounts) {
            List<Integer> numbers = random.ints(sampleCount, minVal, maxVal)
                    .boxed()
                    .toList();
            for (int binCount : binCounts){
                Partition partition = new Partition();

                partition.setNumbers(numbers);
                partition.setBinCount(binCount);
                partition.setCallLimit(this.maxCallCount);

                List<String> row = new ArrayList<>(Arrays.asList(String.valueOf(binCount), String.valueOf(sampleCount)));
                {
                    //Greedy
                    log.info(greedyMessage.format(new Object[]{binCount, sampleCount}));
                    long start = System.currentTimeMillis();
                    partition.compute(Partition.Algorithm.GREEDY);
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    log.info("\tCalls: " + partition.getCallCount() + "\tBottomCalls: " + partition.getBottomCalls());
                    row.add("|");
                    row.add(String.valueOf(partition.getRange()));
                    row.add(String.valueOf(timeElapsed));
                    row.add(String.valueOf(partition.getCallCount()));
                }
                //DS Tree
                {
                    log.info(dsTreeMessage.format(new Object[]{binCount, sampleCount}));
                    long start = System.currentTimeMillis();
                    partition.compute(Partition.Algorithm.DSTREE);
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    log.info("\tCalls: " + partition.getCallCount() + "\tBottomCalls: " + partition.getBottomCalls());
                    row.add("|");
                    row.add(String.valueOf(partition.getRange()));
                    row.add(String.valueOf(timeElapsed));
                    row.add(String.valueOf(partition.getCallCount()));
                }

                // IS Tree
                {
                    log.info(isTreeMessage.format(new Object[]{binCount, sampleCount}));
                    long start = System.currentTimeMillis();
                    partition.compute(Partition.Algorithm.ISTREE);
                    long finish = System.currentTimeMillis();
                    long timeElapsed = finish - start;
                    log.info("\tCalls: " + partition.getCallCount() + "\tBottomCalls: " + partition.getBottomCalls());
                    row.add("|");
                    row.add(String.valueOf(partition.getRange()));
                    row.add(String.valueOf(timeElapsed));
                    row.add(String.valueOf(partition.getCallCount()));

                    rows.add(row);
                }
            }
        }
        System.out.println(PartitionBenchmark.formatAsTable(rows));
    }

    public static String formatAsTable(List<List<String>> rows)
    {
        //https://stackoverflow.com/questions/18672643/how-to-print-a-table-of-information-in-java
        int[] maxLengths = new int[rows.get(0).size()];
        for (List<String> row : rows)
        {
            for (int i = 0; i < row.size(); i++)
            {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        }

        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths)
        {
            formatBuilder.append("%-").append(maxLength + 2).append("s");
        }
        String format = formatBuilder.toString();

        StringBuilder result = new StringBuilder();
        for (List<String> row : rows)
        {
            result.append(String.format(format, (Object[]) row.toArray(new String[0]))).append("\n");
        }
        return result.toString();
    }
}
