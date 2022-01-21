package com.github.ivansavelyev.votingsystem.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

import static com.github.ivansavelyev.votingsystem.util.TimeUtil.DATE_FORMAT_PATTERN;

@Getter
@Setter
@NoArgsConstructor
public class MenuTo extends BaseTo {

    @NotNull
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    protected LocalDate date;

    @NotNull
    @Positive
    protected Integer restaurantId;

    public MenuTo(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }
}
