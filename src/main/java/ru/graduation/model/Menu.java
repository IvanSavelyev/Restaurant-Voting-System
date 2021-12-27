package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "unique_menu")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Menu extends AbstractNamedEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @NotNull
    @JsonManagedReference
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonIgnore
    private Restaurant restaurant;
}