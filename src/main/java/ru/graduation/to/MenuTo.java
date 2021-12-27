package ru.graduation.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.graduation.model.Dish;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuTo extends BaseTo implements Serializable {

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotNull
    private LocalDate date = LocalDate.now();

    @NotNull
    private List<Dish> dishes;

    public MenuTo(Integer id, String name, LocalDate date, List<Dish> dishes) {
        super(id);
        this.name = name;
        this.date = date;
        this.dishes = dishes;
    }
}
