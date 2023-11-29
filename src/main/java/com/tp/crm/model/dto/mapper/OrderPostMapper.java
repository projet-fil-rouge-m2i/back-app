package com.tp.crm.model.dto.mapper;

import com.tp.crm.model.entity.Order;
import com.tp.crm.model.StateOrder;
import com.tp.crm.model.dto.OrderPostDTO;

public class OrderPostMapper {

    public static Order DtoToEntity(OrderPostDTO dto) {
        Order entity = new Order();
        entity.setTypePresta(dto.getTypePresta());
        entity.setDesignation(dto.getDesignation());
        entity.setNbDays(dto.getNbDays());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setState(dto.getState());

        return entity;
    }
}
