package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"menu_id", "name"},
        name = "unique_menu_dish")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false, columnDefinition = "Decimal(10,2)")
    private float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonBackReference
    @NotNull
    private Menu menu;

    public Dish(Integer id, String name, float price) {
        super(id, name);
        this.price = price;
    }
}
