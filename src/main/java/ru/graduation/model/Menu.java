package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

import static ru.graduation.util.TimeUtil.DATE_FORMAT_PATTERN;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "restaurant_id"}, name = "unique_menu")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Menu extends AbstractNamedEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DATE_FORMAT_PATTERN)
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @JsonManagedReference
    private List<Dish> dishes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonIgnore
    private Restaurant restaurant;


    public Menu(Integer id, String name, LocalDate date) {
        super(id, name);
        this.date = date;
    }

    public Menu(Integer id, String name, LocalDate date, List<Dish> dishes) {
        this(id, name, date);
        this.dishes = dishes;
    }
}