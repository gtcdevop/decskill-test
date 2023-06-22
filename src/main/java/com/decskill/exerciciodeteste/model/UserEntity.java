package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.internal.metadata.facets.Validatable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "users")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 255)
    @JsonProperty("name")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @JsonProperty("password")
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Email is required")
    @JsonProperty("email")
    private String email;

    @Column
    private boolean adminAccess;

}

