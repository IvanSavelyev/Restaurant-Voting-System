package ru.graduation.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dishes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Dish extends AbstractNamedEntity {

    //@ManyToOne(fetch = FetchType.LAZY)
    //@ToString.Exclude
    //private Menu menu;

    @Column(name = "price")
    private Double price;
}
