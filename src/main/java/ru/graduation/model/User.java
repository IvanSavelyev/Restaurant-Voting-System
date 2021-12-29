package ru.graduation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ru.graduation.util.JsonDeserializers;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "unique_email")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"password"})
public class User extends AbstractNamedEntity {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    @Size(max = 128)
    private String email;

    @Column(name = "password", nullable = false)
    @Size(max = 256)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonDeserialize(using = JsonDeserializers.PasswordDeserializer.class)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @BatchSize(size = 200)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles;

    public User(User u) {
        this(u.id, u.name, u.email, u.password, u.roles);
    }

    public User(Integer id, String name, String email, String password, Role role, Role... roles) {
        this(id, name, email, password,  EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password , Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        setRoles(roles);
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void setEmail(String email) {
        this.email = StringUtils.hasText(email) ? email.toLowerCase() : null;
    }
}