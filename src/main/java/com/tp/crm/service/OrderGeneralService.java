package com.tp.crm.service;

import com.tp.crm.model.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderGeneralService {

    //Fonction qui ce rappel dans que le Order à modifier contient les valeur THT et TTC à null
    public void checkTaxe(Order orderModifie, Order orderBaseDonne) {
        System.out.println(orderBaseDonne.getTotalWithTaxe());
        if (orderModifie.getTotalWithTaxe() == null || orderModifie.getTotalExcludeTaxe() == null) {

            if (orderModifie.getTotalWithTaxe() == null) {
                orderModifie.setTotalWithTaxe(orderBaseDonne.getTotalWithTaxe());
            }
            if (orderModifie.getTotalExcludeTaxe() == null) {
                orderModifie.setTotalExcludeTaxe(orderBaseDonne.getTotalWithTaxe());
            }
            checkTaxe(orderModifie, orderBaseDonne);
        }
    }
}
