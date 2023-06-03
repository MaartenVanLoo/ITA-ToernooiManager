package be.ita.toernooimanager.service.util.partitioning;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
class PartitionResult {
    List<IdNumber> numbers;

    public PartitionResult(@NotNull List<IdNumber> numbers) {
        this.numbers =  new ArrayList<>();
        numbers.forEach((e)->this.numbers.add(new IdNumber(e)));
    }

    public void update(List<IdNumber> numbers){
        var  oldIt = this.numbers.iterator();
        var  newIt = numbers.iterator();

        while (oldIt.hasNext()){
            oldIt.next().binId = newIt.next().binId;
        }

        //for (int i = 0; i < numbers.size(); i++) {
        //    this.numbers.get(i).binId = numbers.get(i).binId;
        //}
    }
}

