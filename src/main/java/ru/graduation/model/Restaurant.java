package ru.graduation.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @NotNull
//    @ToString.Exclude
//    private Set<Menu> menu;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @NotNull
//    @ToString.Exclude
//    private Set<Vote> votes;
}
