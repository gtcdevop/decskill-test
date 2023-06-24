package com.decskill.exerciciodeteste.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderitem")
@Data
public class OrderItemEntity implements Serializable  {

    private static final long serialVersionUID = 14L;

    public OrderItemEntity() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @OneToOne(optional = true, cascade = CascadeType.REMOVE)
    @JoinColumn(name="items_id")
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    @Column
    private int quantity;

    @JsonProperty("fullFilled")
    @Column
    private boolean fullFilled;

    @ManyToOne
    @JoinColumn(name= "orders_id")
    private OrderEntity orders;

}
