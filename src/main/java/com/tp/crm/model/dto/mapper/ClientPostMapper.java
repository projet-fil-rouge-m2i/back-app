package com.tp.crm.model.dto.mapper;

import com.tp.crm.model.entity.Client;
import com.tp.crm.model.StateClient;
import com.tp.crm.model.dto.ClientPostDTO;

public class ClientPostMapper {

    public static Client DtoToEntity(ClientPostDTO dto) {
        Client entity = new Client();
        entity.setCompanyName(dto.getCompanyName());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setAddress(dto.getAddress());
        entity.setZipCode(dto.getZipCode());
        entity.setCity(dto.getCity());
        entity.setCountry(dto.getCountry());
        entity.setState(dto.getState());

        return entity;
    }
}
