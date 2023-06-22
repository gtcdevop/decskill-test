package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stock")
@Data
public class StockEntity implements Serializable {

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @Column(nullable = false)
    @JsonProperty("creationDate")
    private Date creationDate;

    @Column(nullable = false)
    @JsonProperty("item")
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    @NotNull("Quantity must be integer")
    @Range(min = 0)
    private Integer quantity;

    private long item;
}
