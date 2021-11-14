package ru.graduation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Menu extends AbstractNamedEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id", nullable = false)
    @NotNull
    private Set<Dish> dishes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    private Restaurant restaurant;
}
