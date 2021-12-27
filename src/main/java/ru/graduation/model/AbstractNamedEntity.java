package ru.graduation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "name")
    @Size(max = 128)
    private String name;

    protected AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
