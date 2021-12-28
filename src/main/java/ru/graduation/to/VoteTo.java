package ru.graduation.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class VoteTo {

    @Size(min = 2, max = 100)
    private String name;
}
