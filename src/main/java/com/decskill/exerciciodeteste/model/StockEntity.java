package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
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

    @JsonProperty("item")
    @OneToOne
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    @Range(min = 0, message = "Valor tens de ser maior ou igual a zero")
    private Integer quantity;

    private long item;
}
