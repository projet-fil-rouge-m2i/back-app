package com.tp.crm.model;

public class OrderPostMapper {

    public static Order DtoToEntity(OrderPostDTO dto) {
        Order entity = new Order();
        entity.setTypePresta(dto.getTypePresta());
        entity.setDesignation(dto.getDesignation());
        entity.setNbDays(dto.getNbDays());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setState(convertStateToInteger(dto.getState()));

        return entity;
    }

    private static Integer convertStateToInteger(StateOrder stateValue) {
        if (stateValue == StateOrder.CANCELED) {
            return 0;
        } else if (stateValue == StateOrder.OPTION) {
            return 1;
        } else if (stateValue == StateOrder.CONFIRMED) {
            return 2;
        }
        return null;
    }
}
