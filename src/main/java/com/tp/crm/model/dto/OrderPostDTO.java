package com.tp.crm.model.dto;

import com.tp.crm.model.StateOrder;
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
public class OrderPostDTO {

    private String typePresta;

    private String designation;

    private Integer idClient;

    private Integer nbDays;

    private BigInteger unitPrice;

    private BigDecimal totalExcludeTaxe;

    private BigDecimal totalWithTaxe;

    private StateOrder state;



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

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
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
}
