package ru.graduation.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonManagedReference
//    @ToString.Exclude
//    private List<Menu> menus;
}
