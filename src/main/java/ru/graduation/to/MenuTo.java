package ru.graduation.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static ru.graduation.util.TimeUtil.DATE_FORMAT_PATTERN;

@Getter
@Setter
@NoArgsConstructor
public class MenuTo extends BaseTo {

    @NotNull
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    protected LocalDate date;

    @NotNull
    protected Integer restaurantId;

    public MenuTo(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }
}
