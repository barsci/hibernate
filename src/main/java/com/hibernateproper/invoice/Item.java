package com.hibernateproper.invoice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Item")
public class Item {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    Product product;

    @NotNull
    @Column(name = "price")
    BigDecimal price;

    @NotNull
    @Column(name = "quantity")
    int quantity;

    @NotNull
    @Column(name = "value")
    BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    Invoice invoice;

    public Item(@NotNull BigDecimal price, @NotNull int quantity, @NotNull BigDecimal value) {
        this.price = price;
        this.quantity = quantity;
        this.value = value;
    }
}
