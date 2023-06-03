package be.ita.toernooimanager.service.util.partitioning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
class Bin {
    int index;
    int currentSize;
    int count;

    public Bin(int index) {
        this.index = index;
    }

    public void addNumber(IdNumber number){
        this.currentSize += number.value;
        number.setBinId(this.index);
    }

    public void removeNumber(IdNumber number){
        this.currentSize -= number.value;
        number.setBinId(0); //assign value to 'remaining bin'
    }

    public void reset(){
        this.currentSize = 0;
    }
}


@Getter
@Setter
@NoArgsConstructor
class Range{
    long min = Long.MAX_VALUE;
    long max = 0;

    public Range(long initial) {
        this.min = this.max = initial;
    }
    public void reset(long initial){
        this.min = this.max = initial;
    }
    public long getRange(){
        return this.max - this.min;
    }
}

@Getter
@Setter
class IdNumber implements Comparable<IdNumber>, Comparator<IdNumber> {
    Integer value;
    int binId=0;

    public IdNumber(int value) {
        this.value=value;
    }
    public IdNumber(IdNumber number){
        this.value = number.value;
        this.binId = number.binId;
    }
    public IdNumber(Integer value) {
        this.value=value;
    }

    public void reset(){
        this.binId=0;
    }


    @Override
    public int compareTo(IdNumber o) {
        return this.getValue().compareTo(o.getValue());
    }

    @Override
    public int compare(IdNumber o1, IdNumber o2) {
        return o1.getValue() - o2.getValue();
    }
}