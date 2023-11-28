package com.tp.crm.model.dto.mapper;


import com.tp.crm.model.Client;
import com.tp.crm.model.dto.ClientPutDto;

public class ClientMapper {
    public static ClientPutDto entityToDto(Client entity){
        ClientPutDto orderDto = new ClientPutDto();

        orderDto.setCompanyName(entity.getCompanyName());
        orderDto.setAddress(entity.getAddress());
        orderDto.setCity(entity.getCity());
        orderDto.setCountry(entity.getCountry());
        orderDto.setEmail(entity.getEmail());
        orderDto.setFirstName(entity.getFirstName());
        orderDto.setLastName(entity.getLastName());
        orderDto.setPhoneNumber(entity.getPhoneNumber());
        orderDto.setZipCode(entity.getZipCode());

        return orderDto;
    }

    public static Client DtoToEntity(ClientPutDto dto){
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

        return entity;
    }
}
