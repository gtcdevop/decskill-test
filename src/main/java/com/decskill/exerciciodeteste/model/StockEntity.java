package com.decskill.exerciciodeteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stock")
@Data
public class StockEntity implements Serializable {

    private static final long serialVersionUID = 5L;

    @Column(nullable = false)
    @JsonProperty("creationDate")
    private Date creationDate;

    @JsonProperty("item")
    @OneToOne
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ItemEntity itemEntity;

    @JsonProperty("quantity")
    @NotNull(message = "Indique o valor 'quantity' ")
    @Range(min = 0, message = "Valor tens de ser maior ou igual a zero")
    private Integer quantity;

    @Id
    @JsonProperty("id")
    private long itemId;
}
