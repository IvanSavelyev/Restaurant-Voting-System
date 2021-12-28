package ru.graduation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id"}, name = "unique_vote_user_vote")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Vote extends AbstractBaseEntity {

    public Vote(Restaurant restaurant, User user) {
        this.restaurant = restaurant;
        this.user = user;
    }

    @Column(name = "vote_date_time", nullable = false)
    @NotNull
    private LocalDateTime voteDateTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    @ToString.Exclude
    @JsonIgnore
    private User user;
}
