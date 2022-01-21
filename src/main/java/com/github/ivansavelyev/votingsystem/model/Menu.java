package com.github.ivansavelyev.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.ivansavelyev.votingsystem.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"menu_date", "restaurant_id"},
        name = "unique_restaurant_menu_date")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Menu extends AbstractBaseEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = TimeUtil.DATE_FORMAT_PATTERN)
    private LocalDate date;

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonIgnore
    @NotNull
    private Restaurant restaurant;

    public Menu(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public Menu(Integer id, LocalDate date, List<Dish> dishes) {
        this(id, date);
        this.dishes = dishes;
    }
}