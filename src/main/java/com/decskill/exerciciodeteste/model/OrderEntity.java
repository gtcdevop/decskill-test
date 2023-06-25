package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 12L;

    public OrderEntity() {}

    public OrderEntity(List<OrderItemEntity> orderItem, Long userId) {
        OrderItem = orderItem;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("creationDate")
    private Date creationDate;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("itemsList")
    @NotNull(message = "Adicione os items na lista usando id e quantity")
    private List<OrderItemEntity> OrderItem;

    @JsonProperty("user")
    @OneToOne(optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "users_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private UserEntity userEntity;

    @Column(nullable = false, columnDefinition = "boolean default false")
    @JsonProperty("order_fullfilled")
    @Transient
    private boolean fullfilled;

    @JsonProperty("userId")
    private Long userId;

}
