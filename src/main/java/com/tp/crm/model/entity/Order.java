package com.tp.crm.model.entity;

import com.tp.crm.model.StateOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

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
    @Enumerated(EnumType.ORDINAL)
    private StateOrder state;

    public Order() {
    }

    public Order(String typePresta, String designation, Client client, Integer nbDays, BigInteger unitPrice, BigDecimal totalExcludeTaxe, BigDecimal totalWithTaxe, StateOrder state) {
        this.typePresta = typePresta;
        this.designation = designation;
        this.client = client;
        this.nbDays = nbDays;
        this.unitPrice = unitPrice;
        this.totalExcludeTaxe = totalExcludeTaxe;
        this.totalWithTaxe = totalWithTaxe;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypePresta() {
        return typePresta;
    }

    public void setTypePresta(String typePresta) {
        this.typePresta = typePresta;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getNbDays() {
        return nbDays;
    }

    public void setNbDays(Integer nbDays) {
        this.nbDays = nbDays;
    }

    public BigInteger getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigInteger unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalExcludeTaxe() {
        return totalExcludeTaxe;
    }

    public void setTotalExcludeTaxe(BigDecimal totalExcludeTaxe) {
        this.totalExcludeTaxe = totalExcludeTaxe;
    }

    public BigDecimal getTotalWithTaxe() {
        return totalWithTaxe;
    }

    public void setTotalWithTaxe(BigDecimal totalWithTaxe) {
        this.totalWithTaxe = totalWithTaxe;
    }

    public StateOrder getState() {
        return state;
    }

    public void setState(StateOrder state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", typePresta='" + typePresta + '\'' +
                ", designation='" + designation + '\'' +
                ", client=" + client +
                ", nbDays=" + nbDays +
                ", unitPrice=" + unitPrice +
                ", totalExcludeTaxe=" + totalExcludeTaxe +
                ", totalWithTaxe=" + totalWithTaxe +
                ", state=" + state +
                '}';
    }
}
