package com.github.ivansavelyev.votingsystem.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(
        columnNames = {"user_id", "vote_date"},
        name = "unique_user_vote_date")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vote extends AbstractBaseEntity {

    @Column(name = "vote_date", nullable = false)
    @NotNull
    private LocalDate voteDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Vote(Integer id) {
        super(id);
    }

    public Vote(Restaurant restaurant, User user) {
        this.restaurant = restaurant;
        this.user = user;
    }

    public Vote(Integer id, Restaurant restaurant, User user) {
        this(id);
        this.restaurant = restaurant;
        this.user = user;
    }
}
