package ru.graduation.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.graduation.HasId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class DishTo extends BaseTo {

    @NotBlank
    @Size(max = 128)
    @NotNull
    protected String name;

    @NotNull
    protected Integer price;

    @NotNull
    protected Integer menuId;

    public DishTo(Integer id, String name, Integer price) {
        super(id);
        this.name = name;
        this.price = price;
    }
}
