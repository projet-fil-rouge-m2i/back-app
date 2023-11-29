package com.tp.crm.model.dto.mapper;


import com.tp.crm.model.StateOrder;
import com.tp.crm.model.dto.OrderDTO;
import com.tp.crm.model.entity.Client;
import com.tp.crm.model.dto.ClientPutDTO;
import com.tp.crm.model.entity.Order;

public class ClientMapper {
    public static ClientPutDTO entityToDto(Client entity){
        ClientPutDTO orderDto = new ClientPutDTO();

        orderDto.setCompanyName(entity.getCompanyName());
        orderDto.setAddress(entity.getAddress());
        orderDto.setCity(entity.getCity());
        orderDto.setCountry(entity.getCountry());
        orderDto.setEmail(entity.getEmail());
        orderDto.setFirstName(entity.getFirstName());
        orderDto.setLastName(entity.getLastName());
        orderDto.setPhoneNumber(entity.getPhoneNumber());
        orderDto.setZipCode(entity.getZipCode());
        orderDto.setState(entity.getState());

        return orderDto;
    }

    public static Client DtoToEntity(ClientPutDTO dto){
        Client entity = new Client();

        entity.setId(dto.getId());
        entity.setCompanyName(dto.getCompanyName());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setZipCode(dto.getZipCode());
        entity.setState(dto.getState());

        return entity;
    }

}
