package ru.graduation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(
        columnNames = "name",
        name = "unique_restaurant")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @ToString.Exclude
    @JsonIgnore
    private Menu menu;
}
