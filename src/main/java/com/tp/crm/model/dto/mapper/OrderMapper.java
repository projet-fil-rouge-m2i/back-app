package com.tp.crm.model.dto.mapper;

import com.tp.crm.model.dto.OrderDTO;
import com.tp.crm.model.entity.Order;
import com.tp.crm.model.dto.OrderPutDTO;

public class OrderMapper {
//    public static OrderPutDTO entityToDto(Order entity){
//        OrderPutDTO orderDto = new OrderPutDTO();
//
//        orderDto.setIdClient(entity.getClient().getId());
//        orderDto.setDesignation(entity.getDesignation());
//        orderDto.setNbDays(entity.getNbDays());
//        orderDto.setTypePresta(entity.getTypePresta());
//        orderDto.setUnitPrice(entity.getUnitPrice());
//        orderDto.setTotalExcludeTaxe(entity.getTotalExcludeTaxe());
//        orderDto.setTotalWithTaxe(entity.getTotalWithTaxe());
//
//        return orderDto;
//    }

    public static Order DtoToEntity(OrderPutDTO dto){
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setUnitPrice(dto.getUnitPrice());
        entity.setDesignation(dto.getDesignation());
        entity.setNbDays(dto.getNbDays());
        entity.setTypePresta(dto.getTypePresta());
        return entity;
    }

    public static OrderDTO entityToDto(Order entity) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setTypePresta(entity.getTypePresta());
        orderDTO.setDesignation(entity.getDesignation());
        orderDTO.setClientId(entity.getClient());
        orderDTO.setNbDays(entity.getNbDays());
        orderDTO.setUnitPrice(entity.getUnitPrice());
        orderDTO.setTotalExcludeTaxe(entity.getTotalExcludeTaxe());
        orderDTO.setTotalWithTaxe(entity.getTotalWithTaxe());
        orderDTO.setState(entity.getState());

        return orderDTO;
    }


}
