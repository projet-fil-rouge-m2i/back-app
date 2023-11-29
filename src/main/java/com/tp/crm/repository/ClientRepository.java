package com.tp.crm.repository;

import com.tp.crm.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public Client findByPhoneNumber(String phoneNumber);
    public Client findByEmail(String email);
}
