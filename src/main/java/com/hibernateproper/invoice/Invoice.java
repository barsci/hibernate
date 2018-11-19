package com.hibernateproper.invoice;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "Invoice")
public class Invoice {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "id", unique = true)
    int id;

    @NotNull
    @Column(name = "number")
    String number;

    @OneToMany(
            targetEntity = Item.class,
            mappedBy = "invoice",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    List<Item> itemList = new ArrayList<>();

    public Invoice(@NotNull String number) {
        this.number = number;
    }
}
