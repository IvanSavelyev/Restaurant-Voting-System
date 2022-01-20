package ru.graduation.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishTo {

    protected Integer id;

    @NotBlank
    @Size(max = 128)
    @NotNull
    protected String name;

    @NotNull
    protected Integer price;

    @NotNull
    protected Integer menuId;
}
