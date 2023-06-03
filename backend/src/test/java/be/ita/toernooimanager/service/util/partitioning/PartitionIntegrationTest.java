package be.ita.toernooimanager.service.util.partitioning;

import be.ita.toernooimanager.service.util.partitioning.Exceptions.AlgorithmUnknownException;
import be.ita.toernooimanager.service.util.partitioning.Exceptions.NoBinsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PartitionIntegrationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGreedy(){
        Partition partition = new Partition();
        List<Integer> numbers= Arrays.asList(3,4,5,6,7,10,11,12,13,15,16,19,20);
        partition.setNumbers(numbers);
        partition.setBinCount(3);
        try {
            partition.compute(Partition.Algorithm.GREEDY);
        }catch(AlgorithmUnknownException | NoBinsException e){
            e.printStackTrace();
            fail(); //Should not throw exception
        }

        assertEquals(2,partition.getRange()); // https://www.codeproject.com/Articles/1265125/Fast-and-Practically-perfect-Partition-Problem-Sol#Terms
        assertEquals(3,partition.getCallCount());
        log.info("Greedy call count " + partition.getCallCount());
    }
    @Test
    void testDSTree(){
        Partition partition = new Partition();
        List<Integer> numbers= Arrays.asList(3,4,5,6,7,10,11,12,13,15,16,19,20);
        partition.setNumbers(numbers);
        partition.setBinCount(3);
        try {
            partition.compute(Partition.Algorithm.DSTREE);
        }catch(AlgorithmUnknownException | NoBinsException e){
            e.printStackTrace();
            fail(); //Should not throw exception
        }

        assertEquals(0,partition.getRange());
        log.info("DSTree call count " + partition.getCallCount());
    }

    @Test
    void testISTree(){
        Partition partition = new Partition();
        List<Integer> numbers= Arrays.asList(3,4,5,6,7,10,11,12,13,15,16,19,20);
        partition.setNumbers(numbers);
        partition.setBinCount(3);
        try {
            partition.compute(Partition.Algorithm.ISTREE);
        }catch(AlgorithmUnknownException | NoBinsException e){
            e.printStackTrace();
            fail(); //Should not throw exception
        }

        assertEquals(0,partition.getRange());
        log.info("ISTree call count " + partition.getCallCount());
    }

    @Test
    void divideITA2023(){
        //U15 weging 1
        {
            List<Integer> U15weg1 = Arrays.asList(3, 23, 19, 30, 10, 24, 43, 49);
            Partition partition = new Partition();
            partition.setNumbers(U15weg1);
            partition.setBinCount(4); // 4 matten
            try {
                List<IdNumber> partitions = partition.compute(Partition.Algorithm.ISTREE);
                log.info("\nU15 weging 1\n\tcall count " + partition.getCallCount());
                log.info("range\t" + partition.getRange());
                partitions.forEach(System.out::println);
            }catch(AlgorithmUnknownException | NoBinsException e){
                e.printStackTrace();
                fail(); //Should not throw exception
            }
        }
        //U15 weging 2
        {
            List<Integer> U15weg2 = Arrays.asList(33, 12, 12, 3, 46, 46, 37, 19, 24);
            Partition partition = new Partition();
            partition.setNumbers(U15weg2);
            partition.setBinCount(4); // 4 matten
            try {
                List<IdNumber> partitions = partition.compute(Partition.Algorithm.ISTREE);
                log.info("\nU15 weging 2\n\tcall count " + partition.getCallCount());
                log.info("range\t" + partition.getRange());
                partitions.forEach(System.out::println);
            }catch(AlgorithmUnknownException | NoBinsException e){
                e.printStackTrace();
                fail(); //Should not throw exception
            }

        }
        //U18 weging 1
        {
            List<Integer> U18weg1 = Arrays.asList(12,23,15,22,24,45,79);
            Partition partition = new Partition();
            partition.setNumbers(U18weg1);
            partition.setBinCount(4); // 4 matten
            try {
                List<IdNumber> partitions = partition.compute(Partition.Algorithm.ISTREE);
                log.info("\nU18 weging 1\n\tcall count " + partition.getCallCount());
                log.info("range\t" + partition.getRange());
                partitions.forEach(System.out::println);
            }catch(AlgorithmUnknownException | NoBinsException e){
                e.printStackTrace();
                fail(); //Should not throw exception
            }

        }
        //U18 weging 2
        {
            List<Integer> U18weg2 = Arrays.asList(49,21,23,3,67,43,23,10,6);
            Partition partition = new Partition();
            partition.setNumbers(U18weg2);
            partition.setBinCount(4); // 4 matten
            try {
                List<IdNumber> partitions = partition.compute(Partition.Algorithm.ISTREE);
                log.info("\nU18 weging 2\n\tcall count " + partition.getCallCount());
                log.info("range\t" + partition.getRange());
                partitions.forEach(System.out::println);
            }catch(AlgorithmUnknownException | NoBinsException e){
                e.printStackTrace();
                fail(); //Should not throw exception
            }
        }
    }
}