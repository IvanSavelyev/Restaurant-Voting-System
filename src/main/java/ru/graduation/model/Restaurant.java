package ru.graduation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "unique_restaurant")})
@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {
}
