package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true)
public class Menu extends AbstractNamedEntity {

    @Column(name = "menu_date", nullable = false)
    @NotNull
    private LocalDate date = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    @NotNull
    @JsonIgnore
    private List<Dish> dishes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonBackReference
    @JsonIgnore
    private Restaurant restaurant;

    public Menu(Integer id, String name, LocalDate date) {
        super(id, name);
        this.date = date;
    }
}