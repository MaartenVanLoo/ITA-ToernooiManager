package be.ita.toernooimanager.service.util.partitioning;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
class Result {
    List<IdNumber> numbers;
    List<Bin> bins;
    Range range;


    public Result(@NotNull List<IdNumber> numbers) {
        this.numbers =  new ArrayList<>();
        numbers.forEach((e)->this.numbers.add(new IdNumber(e)));

        this.range=new Range();
    }

    public void updateNumbers(List<IdNumber> numbers){
        var  oldIt = this.numbers.iterator();
        var  newIt = numbers.iterator();

        while (oldIt.hasNext()){
            oldIt.next().binId = newIt.next().binId;
        }
        //for (int i = 0; i < numbers.size(); i++) {
        //    this.numbers.get(i).binId = numbers.get(i).binId;
        //}
    }
    public void updateRange(Range range){
        this.range.min = range.min;
        this.range.max = range.max;
    }
    public Range computeRange(){
        this.range.min= this.range.max = this.bins.get(0).currentSize;
        bins.forEach((ss)->{
            if (ss.currentSize < range.min) range.min = ss.currentSize;
            else if (ss.currentSize > range.max) range.max = ss.currentSize;
        });
        return this.range;
    }

    public void reset(){
        this.numbers.forEach(IdNumber::reset);
        this.bins.forEach(Bin::reset);
    }

    public IdNumber getLastNumber(){
        return this.numbers.get(this.numbers.size()-1);
    }
}

