package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "name"}, name = "unique_dish")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
@ToString(callSuper = true)
public class Dish extends AbstractNamedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Menu menu;

    @Column(name = "price", nullable = false)
    @NotNull
    private float price;

    public Dish(Integer id, String name, Float price) {
        super(id, name);
        this.price = price;
    }
}
