package com.tp.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="type_presta", nullable = false, length = 100)
    private String typePresta;

    @Column(name="designation", nullable = false, length = 100)
    private String designation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name="nb_days", nullable = false)
    private Integer nbDays;

    @Column(name="unit_price", nullable = false)
    private BigInteger unitPrice;

    @Column(name="total_exclude_taxe", nullable = false, updatable = false, insertable = false)
    private BigDecimal totalExcludeTaxe;

    @Column(name="total_with_taxe", nullable = false, updatable = false, insertable = false)
    private BigDecimal totalWithTaxe;

    @Column(name="state", nullable = false)
    private Integer state;
}
