package ru.albina.user.domain;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@Accessors(chain = true)
public class UserEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 200)
    @NotNull
    @Column(name = "login", nullable = false, length = 200)
    private String login;

    @Size(max = 200)
    @NotNull
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Size(max = 100)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "middle_name", nullable = false, length = 100)
    private String middleName;

    @Size(max = 100)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;


    @Type(
            value = ListArrayType.class,
            parameters = {
                    @Parameter(
                            name = ListArrayType.SQL_ARRAY_TYPE,
                            value = "varchar"
                    )
            }
    )
    @Column(name = "roles", columnDefinition = "_varchar")
    private Set<Role> roles = new HashSet<>();

    @Formula(value = "concat(last_name, ' ', first_name, ' ', middle_name)")
    private String fullName;

}