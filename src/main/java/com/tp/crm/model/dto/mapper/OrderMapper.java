package com.tp.crm.model.dto.mapper;

import com.tp.crm.model.Order;
import com.tp.crm.model.dto.OrderPutDto;
import jakarta.persistence.Entity;

public class OrderMapper {
    public static OrderPutDto entityToDto(Order entity){
        OrderPutDto orderDto = new OrderPutDto();

        orderDto.setIdClient(entity.getClient().getId());
        orderDto.setDesignation(entity.getDesignation());
        orderDto.setNbDays(entity.getNbDays());
        orderDto.setTypePresta(entity.getTypePresta());
        orderDto.setUnitPrice(entity.getUnitPrice());
        orderDto.setTotalExcludeTaxe(entity.getTotalExcludeTaxe());
        orderDto.setTotalWithTaxe(entity.getTotalWithTaxe());

        return orderDto;
    }

    public static Order DtoToEntity(OrderPutDto dto){
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setDesignation(dto.getDesignation());
        entity.setNbDays(dto.getNbDays());
        entity.setTypePresta(dto.getTypePresta());
        return entity;
    }
}
