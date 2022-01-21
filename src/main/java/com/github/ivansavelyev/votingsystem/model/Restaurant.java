package com.github.ivansavelyev.votingsystem.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;


@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(
        columnNames = "name",
        name = "unique_restaurant")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<Menu> menu;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }
}
