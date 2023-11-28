package com.tp.crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name="first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name="email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name="phone_number", unique = true, length = 15)
    private String phoneNumber;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="zip_code", nullable = false, length = 30)
    private String zipCode;

    @Column(name="city", nullable = false, length = 30)
    private String city;

    @Column(name="country", nullable = false, length = 100)
    private String country;

    @Column(name="state", nullable = false)
    private Integer state;

}
