package com.github.ivansavelyev.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"menu_id", "name"},
        name = "unique_menu_dish")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Dish extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    @JsonIgnore
    private Menu menu;

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
    }
}
