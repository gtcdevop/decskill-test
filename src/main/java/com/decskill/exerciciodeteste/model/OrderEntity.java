package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    public OrderEntity() {}

    public OrderEntity(Long id, int quantity, Long userId, Long itemId) {
        this.id = id;
        this.quantity = quantity;
        this.userId = userId;
        this.itemId = itemId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("creationDate")
    private Date creationDate;

    @JsonProperty("item")
    @OneToOne(optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name= "items_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("user")
    @OneToOne(optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "users_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private UserEntity userEntity;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("itemId")
    private Long itemId;

}
