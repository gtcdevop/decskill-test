package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 12L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("creationDate")
    private Date creationDate;

    @JsonProperty("item")
    @OneToOne(optional = true)
    @JoinColumn(name= "items_id")
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("user")
    @OneToOne(optional = true)
    @JoinColumn(name = "users_id")
    private UserEntity userEntity;

    private Long user;

    private Long item;
}
