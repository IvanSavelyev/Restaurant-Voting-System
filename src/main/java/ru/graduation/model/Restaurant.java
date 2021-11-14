package ru.graduation.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Restaurant extends AbstractNamedEntity{

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    //@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    private Set<Menu> menu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    //@JoinColumn(name = "id", nullable = false, insertable = false, updatable = false)
    @NotNull
    @ToString.Exclude
    private Set<Vote> votes;
}
