package be.ita.toernooimanager.service.util.partitioning;

import be.ita.toernooimanager.service.util.partitioning.AlgorithmUnknownException;
import be.ita.toernooimanager.service.util.partitioning.IdNumber;
import be.ita.toernooimanager.service.util.partitioning.NoBinsException;
import be.ita.toernooimanager.service.util.partitioning.Partition;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class PartitionTest {

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
            List<IdNumber> result = partition.compute(Partition.Algorithm.GREEDY);
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
            List<IdNumber> result = partition.compute(Partition.Algorithm.DSTREE);
        }catch(AlgorithmUnknownException | NoBinsException e){
            e.printStackTrace();
            fail(); //Should not throw exception
        }

        assertEquals(0,partition.getRange());
        log.info("DSTree call count " + partition.getCallCount());
    }
    @Test
    void testGreedyDSTree(){
        Partition partition = new Partition();
        List<Integer> numbers= Arrays.asList(3,4,5,6,7,10,11,12,13,15,16,19,20);
        partition.setNumbers(numbers);
        partition.setBinCount(3);
        try {
            List<IdNumber> result = partition.compute(Partition.Algorithm.GREEDY_DSTREE);
        }catch(AlgorithmUnknownException | NoBinsException e){
            e.printStackTrace();
            fail(); //Should not throw exception
        }

        assertEquals(0,partition.getRange());
        log.info("GreedyDSTree call count " + partition.getCallCount());
    }

    @Test
    void testISTree(){
        Partition partition = new Partition();
        List<Integer> numbers= Arrays.asList(3,4,5,6,7,10,11,12,13,15,16,19,20);
        partition.setNumbers(numbers);
        partition.setBinCount(3);
        try {
            List<IdNumber> result = partition.compute(Partition.Algorithm.ISTREE);
        }catch(AlgorithmUnknownException | NoBinsException e){
            e.printStackTrace();
            fail(); //Should not throw exception
        }

        assertEquals(0,partition.getRange());
        log.info("ISTree call count " + partition.getCallCount());
    }


}