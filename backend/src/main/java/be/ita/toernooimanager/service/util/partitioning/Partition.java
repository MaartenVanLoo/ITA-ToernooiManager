package be.ita.toernooimanager.service.util.partitioning;

import jakarta.persistence.Id;
import lombok.AccessLevel;
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

    @Getter
    @Setter
    private long callLimit = -1; //max calls for dsTree ( value <= 0 = infinite calls (until overflow of long :p) );
    @Getter
    private long bottomCalls = 0;
    @Getter
    private long callCount; //current number of calls for dsTree


    private long minSum; //Lower bin size for dsTree
    private long maxSum; //Upper bin size for dsTree
    @Getter
    private long range; //The value we want to minimize


    private List<IdNumber> numbers;
    private Flags flag = Flags.NONE;

    @Getter
    @Setter
    private int binCount=5;

    private List<Bin> bins;


    // Result of calculation
    PartitionResult result;

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
            return this.result.getNumbers();
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
                this.numbers.forEach(IdNumber::reset);
                this.bins.forEach(Bin::reset);
                this.dsTree(0, this.binCount-1); //Limit the initial boundaries by first computing greedy (O(n)) followed by dsTree(O(n!))
            }
        }else if (method==Algorithm.ISTREE){

            this.isTree(); //Limit the initial boundaries by first computing greedy (O(n)) followed by dsTree(O(n!))

        }else{
            throw new AlgorithmUnknownException();
        }

        return this.result.getNumbers();
    }
    private void initCompute(){
        //Reset all numbers
        this.numbers.forEach(IdNumber::reset);
        this.numbers.sort(Collections.reverseOrder());

        //Create bins
        this.bins = new ArrayList<>();
        //asign bins the id which corresponds to their index
        for (int i = 0; i < this.binCount; i++){
            this.bins.add(new Bin(i));
        }

        //Reset counter
        this.callCount = 0;
        this.bottomCalls = 0;
        this.minSum=0;
        this.maxSum = Integer.MAX_VALUE;
        this.range = this.maxSum - this.minSum;
        this.flag = Flags.NONE;

        //Create result variable
        this.result= new PartitionResult(this.numbers);
    }

    /**
     * Greedy search
     */
    private void greedy(){
        for (IdNumber number : this.numbers){
            // Look for smallest bin
            Bin smallestBin = getSmallestBin();
            //append number to the smallest bin
            smallestBin.addNumber(number);
        }
        Range range = this.computeRange(this.bins);
        this.minSum = range.getMin();
        this.maxSum = range.getMax();
        this.range = this.maxSum - this.minSum;

        this.result.update(this.numbers);
        if (this.range == 0) this.flag = Flags.COMPLETE; //perfect solution found => terminate computation

        this.callCount += this.binCount; // Would be the same as a recursion with depth n
    }

    /**
     * Dynamic search tree
     */
    private void dsTree(int number_index, int bin_index){
        if (this.flag != Flags.NONE) return;

        if (++ this.callCount == callLimit){
            this.flag = Flags.LIMIT;
            return;
        }

        Bin curr_bin = this.bins.get(bin_index);
        if (bin_index > 0){ //Not last bin
            for (int i = number_index; i < this.numbers.size(); i++){       //Loop through the numbers starting from the current one
                IdNumber number = this.numbers.get(i);
                if (number.binId == 0 && number.getValue() + curr_bin.getCurrentSize() < this.maxSum){
                    // number = unassigned (all unassigned values are in the 'last bin') &&
                    // adding this number to the current bin will not go over the upper bin limit
                    curr_bin.addNumber(number);     //Add number to this bin

                    if (number_index+1 != this.numbers.size()){ // Check to make sure the last value isn't reached
                        this.dsTree(number_index+1,bin_index);  // Try to fit the next value in the current bin
                    }

                    if (curr_bin.getCurrentSize() > this.minSum){ //Current bin full?
                        this.dsTree(0,bin_index-1); //start filling the next bin
                    }

                    curr_bin.removeNumber(number); //Remove the number from the bin
                }
            }
        }else{ //Last bin
            this.bottomCalls++;
            //accumulate sum for the last bin
            for (IdNumber number : this.numbers){
                if (number.binId == 0) curr_bin.currentSize+=number.value;
            }

            //compute range from this iteration
            Range range = this.computeRange(this.bins);
            long curr_min = range.getMin();
            long curr_max = range.getMax();
            long curr_range = curr_max - curr_min;

            if (curr_range < this.range){
                this.minSum = curr_min;
                this.maxSum = curr_max;
                this.range = curr_range;
                this.result.update(this.numbers); //copy current state

                if (this.range == 0) this.flag = Flags.COMPLETE; //perfect solution found => terminate computation
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

        double target = (double)this.numbers.stream().mapToInt(IdNumber::getValue).sum()/this.binCount;
        int margin = target < this.numbers.get(0).value?
                this.numbers.get(0).value - (int)target + 2 : 1;

        long prevIterationRange = this.range; //result from greedy!
        PartitionResult prevIterationResult = new PartitionResult(this.result.numbers); //save result from greedy!

        long limitPerIteration = this.callLimit;
        long totalCalls = 0;
        //do iterations
        do{
            //accumulate & reset counters
            totalCalls += this.callCount;
            this.callCount = 0;
            this.flag = Flags.NONE;

            //Reset all numbers & bins
            this.numbers.forEach(IdNumber::reset);
            this.bins.forEach(Bin::reset);
            //Set boundaries for this iteration
            this.minSum = (long)(target - margin);
            this.maxSum = (long)(target + margin);

            this.dsTree(0, this.binCount-1);
            if (this.flag == Flags.COMPLETE      // Perfect result found
            || (margin*=2) >= prevIterationRange       // Expanding would yield minimum values below ?0
            || this.range > prevIterationRange){ // Prev itt was better
                break;
            }
            if (this.range < prevIterationRange){ //Better solution found
                break;
            }
            //save this iteration if
            prevIterationRange = this.range; //result from greedy!
            prevIterationResult.update(this.result.numbers); //save result from greedy!
        } while(true);
        this.callCount += totalCalls;
        if (this.range < prevIterationRange) return; //best result from last iteration already stored

        //Prev iteration was better; replace the current one
        this.result.update(prevIterationResult.numbers);
        this.range = prevIterationRange;
    }
    //region Utilities
    private Bin getSmallestBin(){
        return this.bins.stream().min(Comparator.comparing(Bin::getCurrentSize))
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
