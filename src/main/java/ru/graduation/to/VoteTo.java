package ru.graduation.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
@Getter
@Setter
public class VoteTo {

    @Size(min = 2, max = 100)
    private String name;

//    @Positive
//    private int total;

    public VoteTo(String name) {
        this.name = name;
//        this.total = total;
    }
}
