package com.decskill.exerciciodeteste.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "orderitem")
@Data
public class OrderItemEntity implements Serializable {

    private static final long serialVersionUID = 14L;

    public OrderItemEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long orderItemId;

    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "items_id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    @Column
    @NotNull(message = "quantity nao pode ser nulo")
    @Range(min = 0)
    private Integer quantity;

    @JsonProperty("fullFilled")
    @Column
    private boolean fullFilled;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    @JsonIgnore
    private OrderEntity orders;

    @JsonProperty("id")
    private Long itemId;

}
