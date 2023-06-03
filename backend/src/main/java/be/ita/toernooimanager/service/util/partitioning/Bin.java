package be.ita.toernooimanager.service.util.partitioning;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
class Bin {
    int index;
    int currentSize;

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
@NoArgsConstructor
@AllArgsConstructor
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
class IdNumber implements Comparable<IdNumber> {
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
    public String toString() {
        return "IdNumber{" +
                "value=" + value +
                ", binId=" + binId +
                '}';
    }
}