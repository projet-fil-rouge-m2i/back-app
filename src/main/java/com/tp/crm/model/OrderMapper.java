package com.tp.crm.model;

import com.tp.crm.model.Order;

public class OrderMapper {
    public static OrderDTO entityToDto(Order entity) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setTypePresta(entity.getTypePresta());
        orderDTO.setDesignation(entity.getDesignation());
        orderDTO.setClientId(entity.getClient());
        orderDTO.setNbDays(entity.getNbDays());
        orderDTO.setUnitPrice(entity.getUnitPrice());
        orderDTO.setTotalExcludeTaxe(entity.getTotalExcludeTaxe());
        orderDTO.setTotalWithTaxe(entity.getTotalWithTaxe());
        orderDTO.setState(convertIntegerToState(entity.getState()));

        return orderDTO;
    }
    private static StateOrder convertIntegerToState(Integer stateValue) {
         if (stateValue == 0) {
             return StateOrder.CANCELED;
         } else if (stateValue == 1) {
             return StateOrder.OPTION;
         } else {
             return StateOrder.CONFIRMED;
         }
    }

}
