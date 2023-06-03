package be.ita.toernooimanager.service.util.partitioning;

import be.ita.toernooimanager.service.util.partitioning.Exceptions.AlgorithmUnknownException;
import be.ita.toernooimanager.service.util.partitioning.Exceptions.NoBinsException;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Partition {
    public enum Algorithm {
        GREEDY,       //only greedy
        DSTREE,       //only DS tree
        ISTREE,       //only IS tree
        GREEDY_DSTREE //Combined greedy dstree
    }
    private enum Flags{
        NONE ,
        COMPLETE,
        LIMIT;


    }

    /** flag of DSTree() completion by limit */
    final static byte LIM_FLAG = 0x1;
    /** flag of DSTree() completion by 'perfect' result */
    final static byte PERF_FLAG = 0x2;

    @Getter
    @Setter
    private long callLimit = -1; //max calls for dsTree ( value <= 0 = infinite calls (until overflow of long :p) );
    @Getter
    private long bottomCalls = 0;
    @Getter
    private long callCount; //current number of calls for dsTree

    float target;
    boolean targetIsFraction;

    private long minSum; //Lower bin size for dsTree
    private long maxSum; //Upper bin size for dsTree
    @Getter
    private long range; //The value we want to minimize
    private long numbersSum; //Sum of all values in "numbers"

    private List<IdNumber> numbers;
    private Flags flag = Flags.NONE;
    byte complete;
    void raiseComplFlag(byte flag) { complete |= flag; }
    boolean isCompleteByPerfect() { return (complete & PERF_FLAG) != 0; }
    boolean isResultPerfect() { return this.range == 0 || (this.targetIsFraction && this.range == 1); }

    @Getter
    @Setter
    private int binCount=5;


    // Result of calculation
    Result currentResult;   //Working result object
    Result bestResult;      // Best result in a certain itertion (used in DSTree)
    Result finalResult;     // Overall best result


    public Partition() {}

    public void setNumbers(List<Integer> numbers){
        this.numbers = new ArrayList<>();
        numbers.stream().map(IdNumber::new).forEach(e->this.numbers.add(e));
    }

    public List<IdNumber> compute(Algorithm method) throws AlgorithmUnknownException, NoBinsException {
        if (this.binCount == 0){
            throw new NoBinsException();
        }
        this.initCompute();

        if (this.numbers.size() < this.binCount){ //In this case greedy = optimal
            this.greedy();
            return this.finalResult.numbers;
        }

        if (method == Algorithm.GREEDY){    //O(n)
            this.greedy();
        } else if (method == Algorithm.DSTREE) { //O(n!)
            //We can estimate the maximum value of a single bin to be no more than the average value + the largest value.
            double sum = (double) this.numbers.stream().mapToInt(IdNumber::getValue).sum();
            this.maxSum = (long)(Math.max(Math.ceil(sum/this.binCount) + this.numbers.get(this.numbers.size() -1 ).getValue(),this.numbers.get(this.numbers.size()-1).getValue()));
            this.dsTree(0, this.binCount-1);
        } else if (method == Algorithm.GREEDY_DSTREE){ // O(n!) but should be faster //TODO: test if faster
            this.greedy();
            if (this.flag != Flags.COMPLETE) { //Flag = complete if greedy range = 0
                //Reset all numbers & bins to restart partition but keep current ranges!
                this.currentResult.reset();
                this.dsTree(0, this.binCount-1); //Limit the initial boundaries by first computing greedy (O(n)) followed by dsTree(O(n!))
            }
        }else if (method==Algorithm.ISTREE){

            this.isTree(); //Limit the initial boundaries by first computing greedy (O(n)) followed by dsTree(O(n!))

        }else{
            throw new AlgorithmUnknownException();
        }

        return this.finalResult.getNumbers();
    }
    private void initCompute(){
        //Reset all numbers
        this.numbers.forEach(IdNumber::reset);
        this.numbers.sort(Collections.reverseOrder());

        //Create result objects
        this.currentResult = new Result(this.numbers);
        this.bestResult = new Result(this.numbers);
        this.finalResult = new Result(this.numbers);

        // Create bins
        this.currentResult.bins = new ArrayList<>();
        // assign bins the id which corresponds to their index
        for (int i = 0; i < this.binCount; i++){
            this.currentResult.bins.add(new Bin(i));
        }

        //Reset counter
        this.callCount = 0;
        this.bottomCalls = 0;
        this.minSum=0;

        this.maxSum = Integer.MAX_VALUE;
        this.range = this.maxSum - this.minSum;
        this.flag = Flags.NONE;
        this.complete = 0;

        this.numbersSum = this.numbers.stream().mapToInt(IdNumber::getValue).sum();
        this.target = (float)this.numbersSum/this.binCount;
        this.targetIsFraction = (int)this.target != this.target;
    }

    /**
     * Greedy search
     */
    private void greedy(){
        for (IdNumber number : this.numbers){
            // Look for smallest bin
            Bin smallestBin = getSmallestBin(currentResult.bins);
            //append number to the smallest bin
            smallestBin.addNumber(number);
        }
        Range range = currentResult.computeRange();
        this.minSum = range.getMin();
        this.maxSum = range.getMax();
        this.range = this.maxSum - this.minSum;

        this.finalResult.updateNumbers(this.numbers);
        this.finalResult.updateRange(range);
        if (this.isResultPerfect()) {
            this.flag = Flags.COMPLETE; //perfect solution found => terminate computation
            this.raiseComplFlag(PERF_FLAG);
        }

        this.callCount += this.binCount; // Would be the same as a recursion with depth n
    }

    /**
     * Dynamic search tree
     */
    private void dsTree(int number_index, int bin_index){
        //if (this.flag != Flags.NONE) return;
        if (complete != 0) return;
        if (++ this.callCount == callLimit){
            raiseComplFlag(LIM_FLAG);
            this.flag = Flags.LIMIT;
            return;
        }

        Bin curr_bin = this.currentResult.bins.get(bin_index);
        if (bin_index != 0){ //Not last bin
            if (this.currentResult.getLastNumber().getValue() + curr_bin.currentSize >= this.maxSum) return; //Every value will fail to be added to this bin, hence no need to check them all
            for (int i = number_index; i < this.currentResult.numbers.size(); i++){       //Loop through the numbers starting from the current one
                IdNumber number = this.currentResult.numbers.get(i);
                if (number.binId == 0 && number.getValue() + curr_bin.getCurrentSize() < this.maxSum){
                    // number = unassigned (all unassigned values are in the 'last bin') &&
                    // adding this number to the current bin will not go over the upper bin limit
                    //curr_bin.addNumber(number);     //Add number to this bin
                    curr_bin.currentSize += number.value;
                    curr_bin.count++;
                    number.binId = bin_index;

                    if (number_index+1 < this.numbers.size()){ // Check to make sure the last value isn't reached
                        this.dsTree(number_index+1,bin_index);  // Try to fit the next value in the current bin
                    }

                    if (curr_bin.getCurrentSize() > this.minSum){ //Current bin full?
                        this.dsTree(0,bin_index-1); //start filling the next bin
                    }

                    //curr_bin.removeNumber(number); //Remove the number from the bin
                    curr_bin.currentSize -= number.value;
                    curr_bin.count--;
                    number.binId = 0;
                }
            }
        }else{ //Last bin
            this.bottomCalls++;
            //accumulate sum for the last bin
            //for (IdNumber number : this.currentResult.numbers){
            //    if (number.binId == 0) curr_bin.currentSize+=number.value;
            //}
            long binSum = 0;
            for (Bin bin : this.currentResult.bins){
                if (bin.index == 0) continue;
                binSum += bin.currentSize;
            }
            curr_bin.currentSize = (int)(this.numbersSum - binSum);

            //compute range from this iteration
            Range range = this.currentResult.computeRange();

            if (range.getRange() < this.finalResult.range.getRange()){
                this.minSum = range.min;
                this.maxSum = range.max;
                this.range = range.getRange();
                this.bestResult.updateNumbers(this.numbers); //copy current state
                this.bestResult.updateRange(range);
                this.finalResult.updateNumbers(this.numbers); //copy current state
                this.finalResult.updateRange(range);
                if (isResultPerfect()) raiseComplFlag(PERF_FLAG); //this.flag = Flags.COMPLETE; //perfect solution found => terminate computation
            }else if (range.getRange() < this.bestResult.range.getRange()){
                this.bestResult.updateNumbers(this.numbers); //copy current state
                this.bestResult.updateRange(range);
            }

            curr_bin.reset(); //reset last bin
        }
    }

    /**
     * Iterative search expansion
     */
    private void isTree(){
        //first do greedy for cheap initial result (and boundary!)
        this.greedy();
        if (this.flag == Flags.COMPLETE) return; //Flag = complete if greedy range = 0


        int margin = target < this.numbers.get(0).value?
                this.numbers.get(0).value - (int)target + 2 : 1;

        this.bestResult.range = new Range();

        long limitPerIteration = this.callLimit;
        long totalCalls = 0;
        //do iterations
        do{
            //accumulate & reset counters
            totalCalls += this.callCount;
            this.callCount = 0;
            this.flag = Flags.NONE;
            this.complete=0;

            //Reset all numbers & bins
            this.currentResult.reset();

            //Set boundaries for this iteration
            this.minSum = (long)(target - margin);
            this.maxSum = (long)(target + margin);

            long start = System.currentTimeMillis();
            this.dsTree(0, this.binCount-1);
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            if (this.callCount >= 1e6) System.out.println("Elapsed dstree: \t" + timeElapsed);

            if (isCompleteByPerfect()      // Perfect result found
            || (margin*=2) >= this.minSum       // Expanding would yield minimum values below ?0
            || this.currentResult.range.getRange() > this.bestResult.range.getRange()){ // Prev itt was better
                break;
            }
        } while(this.currentResult.range.getRange() != this.finalResult.range.getRange());
        this.callCount += totalCalls;

    }
    //region Utilities
    private Bin getSmallestBin(List<Bin> bins){
        return bins.stream().min(Comparator.comparing(Bin::getCurrentSize))
                .orElseThrow(NoSuchElementException::new);
    }

    private Range computeRange(List<Bin> bins){
        Range range = new Range(bins.get(0).currentSize);
        bins.forEach((ss)->{
            if (ss.currentSize < range.min) range.min = ss.currentSize;
            else if (ss.currentSize > range.max) range.max = ss.currentSize;
        });
        return range;
    }
    //endregion
}
