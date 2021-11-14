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
public class Restaurant extends AbstractNamedEntity{

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "meanu_id", nullable = false)
    @NotNull
    private Set<Menu> menu;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id", nullable = false)
    @NotNull
    private Set<Vote> votes;
}
