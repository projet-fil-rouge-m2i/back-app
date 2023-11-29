package com.tp.crm.model.dto.mapper;

import com.tp.crm.model.dto.ClientGetDTO;
import com.tp.crm.model.dto.ClientPutDTO;
import com.tp.crm.model.entity.Client;

public class ClientGetMapper {

   public static ClientGetDTO entityToDto(Client entity){
      ClientGetDTO clientGetDto = new ClientGetDTO();
      clientGetDto.setIdDuClient(entity.getId());
      clientGetDto.setEntreprise(entity.getCompanyName());
      clientGetDto.setAdresse(entity.getAddress());
      clientGetDto.setVille(entity.getCity());
      clientGetDto.setPays(entity.getCountry());
      clientGetDto.setMail(entity.getEmail());
      clientGetDto.setNom(entity.getFirstName());
      clientGetDto.setPrenom(entity.getLastName());
      clientGetDto.setTelephone(entity.getPhoneNumber());
      clientGetDto.setCodePostal(entity.getZipCode());
      clientGetDto.setStatus(entity.getState());

      return clientGetDto;
   }
}
