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
@ToString
public class Menu extends AbstractNamedEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

//    @OneToMany(fetch = FetchType.LAZY)
//    @NotNull
//    @ToString.Exclude
//    private Set<Dish> dishes;
    @OneToMany(fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    private Set<Dish> dishes;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @ToString.Exclude
    private Restaurant restaurant;
}
