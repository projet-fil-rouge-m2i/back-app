package com.tp.crm.model.dto.mapper;


import com.tp.crm.model.StateOrder;
import com.tp.crm.model.dto.OrderDTO;
import com.tp.crm.model.entity.Client;
import com.tp.crm.model.dto.ClientPutDTO;
import com.tp.crm.model.entity.Order;

public class ClientMapper {
    public static ClientPutDTO entityToDto(Client entity){
        ClientPutDTO clientPutDTO = new ClientPutDTO();

        clientPutDTO.setCompanyName(entity.getCompanyName());
        clientPutDTO.setAddress(entity.getAddress());
        clientPutDTO.setCity(entity.getCity());
        clientPutDTO.setCountry(entity.getCountry());
        clientPutDTO.setEmail(entity.getEmail());
        clientPutDTO.setFirstName(entity.getFirstName());
        clientPutDTO.setLastName(entity.getLastName());
        clientPutDTO.setPhoneNumber(entity.getPhoneNumber());
        clientPutDTO.setZipCode(entity.getZipCode());
        clientPutDTO.setState(entity.getState());

        return clientPutDTO;
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
