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

    private static Integer convertStateToInteger(State stateValue) {
        if (stateValue.equals("CANCELED")) {
            return 0;
        } else if (stateValue.equals("CANCELED")) {
            return 1;
        } else {
            return 2;
        }
    }
}
