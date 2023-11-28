package com.tp.crm.model;

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
        entity.setState(convertStateToInteger(dto.getState()));

        return entity;
    }

    private static Integer convertStateToInteger(StateClient stateValue) {
        return stateValue == StateClient.ACTIVE ? 0 : 1;
    }
}
