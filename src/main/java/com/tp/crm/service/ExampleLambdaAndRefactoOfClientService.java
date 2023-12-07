package com.tp.crm.service;

import com.tp.crm.model.dto.ClientGetDTO;
import com.tp.crm.model.dto.ClientPostDTO;
import com.tp.crm.model.dto.mapper.ClientGetMapper;
import com.tp.crm.model.entity.Client;
import com.tp.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExampleLambdaAndRefactoOfClientService {

    @Autowired
    ClientRepository clientRepository;




    // 1) ************************************** EXEMPLE AVEC LA METHODE numberExistePost de ClientService ***********************************

    /**
     * Méthode actuelle dans la classe ClientService
     *
     * @param clientPostDTO Le DTO contenant les informations du client.
     *
     * @return true si le numéro de téléphone existe déjà dans la bdd, false dans le cas contraire.
     */
    public boolean numberExistePost(ClientPostDTO clientPostDTO) {
        if(clientRepository.findByPhoneNumber(clientPostDTO.getPhoneNumber()) != null){
            return true;
        }
        return false;
    }

    /**
     * Utilisation de la méthode 'Optional', pour simplifier la vérification.
     * Au lieu d'une structure conditionnelle, la méthode emploie 'Optional.ofNullable' pour gérer
     * les cas où le numéro de téléphone pourrait être 'null'. La méthode 'isPresent' est ensuite utilisée pour déterminer
     * si le numéro de téléphone existe déjà dans la bdd.
     *
     * La méthode est plus déclarative pour la vérification de l'existence du numéro.
     *
     * @param clientPostDTO Le DTO contenant les informations du client.
     *
     * @return true si le numéro de téléphone existe déjà dans la bdd, false sinon.
     */
    public boolean refactoNumberExistePost(ClientPostDTO clientPostDTO) {
        return Optional.ofNullable(clientRepository.findByPhoneNumber(clientPostDTO.getPhoneNumber())).isPresent();
    }


    /**
     * Utilisation de 'Optional.ofNullable' pour encapsuler le DTO dans un 'Optional',
     * suivi de l'utilisation de 'map' pour extraire et vérifier le numéro de téléphone.
     * L'utilisation d'une expression lambda au sein de la méthode 'map' joue le rôle de vérification de l'existence du numéro de téléphone.
     * La lambda '(phoneNumber -> clientRepository.findByPhoneNumber(phoneNumber) != null)' prend le numéro de téléphone extrait par la première
     * expression lambda ('ClientPostDTO::getPhoneNumber') et l'utilise pour interroger le repository. Cette interrogation renvoie un objet 'Client'
     * si le numéro de téléphone existe, ou 'null' dans le cas contraire.
     *
     * La lambda convertit alors ce résultat en un boolean : 'true' si un objet 'Client' est trouvé,
     * et 'false' si le résultat est 'null'.
     *
     * PS : '.map(ClientPostDTO::getPhoneNumber)' est la même chose que .map(clientPostDTO -> clientPostDTO.getPhoneNumber())
     *
     * @param clientPostDTO DTO contenant les informations du client.
     *
     * @return true si le numéro de téléphone existe déjà dans la base de données, false sinon.
     */
    public boolean lambdaNumberExistePost(ClientPostDTO clientPostDTO) {
        return Optional.ofNullable(clientPostDTO)
                .map(ClientPostDTO::getPhoneNumber)
                .map(phoneNumber -> clientRepository.findByPhoneNumber(phoneNumber) != null)
                .orElse(false);
    }





    // 2) ************************************** EXEMPLE AVEC LA METHODE emailExistePost de ClientService ***********************************

    // Pour cette partie, pas de commentaires sur les méthodes car c'est quasiment la même chose que l'exemple 1

    public boolean emailExistePost(ClientPostDTO clientPostDTO) {
        if(clientRepository.findByEmail(clientPostDTO.getEmail()) != null){
            return true;
        }
        return false;
    }


    public boolean refactoEmailExistePost(ClientPostDTO clientPostDTO) {
        return Optional.ofNullable(clientRepository.findByEmail(clientPostDTO.getEmail())).isPresent();
    }

    public boolean lambdaEmailExistePost(ClientPostDTO clientPostDTO) {
        return Optional.ofNullable(clientPostDTO)
                .map(ClientPostDTO::getEmail)
                .map(email -> clientRepository.findByEmail(email) != null)
                .orElse(false);
    }




    // 3) ************************************** EXEMPLE AVEC LA METHODE champsVidePost de ClientService ***********************************

    /**
     * Méthode actuelle dans la classe ClientService
     *
     * @param clientPostDTO DTO contenant les données du client à vérifier.
     * @return true si l'un des champs est null, false si tous les champs sont non-null.
     */
    public boolean champsVidePost(ClientPostDTO clientPostDTO) {
        if (clientPostDTO.getCompanyName() == null || clientPostDTO.getFirstName() == null || clientPostDTO.getLastName() == null ||
                clientPostDTO.getEmail() == null || clientPostDTO.getPhoneNumber() == null || clientPostDTO.getAddress() == null
                || clientPostDTO.getZipCode() == null || clientPostDTO.getCity() == null || clientPostDTO.getCountry() == null
                || clientPostDTO.getState() == null) {
            return true;
        }
        return false;
    }


    /**
     * Utilisation de 'Stream.of' pour créer un flux des différents champs de ClientPostDTO.
     * L'utilisation de 'Stream.of' permet de regrouper tous ces champs dans un seul flux(=stream), facilitant ainsi une vérification
     * 'collective' et 'concise'. Au lieu de multiples vérifications 'if', la méthode 'anyMatch' du flux est utilisée pour déterminer
     * si l'un des champs est null.
     *
     * 'Objects::isNull' agit comme une fonction qui est appelée sur chaque champ de ClientPostDTO dans le flux. Elle vérifie si un champ est null
     *
     * Cela rend le code plus lisible et plus compact, car il évite les longues chaînes de conditions.
     *
     * @param clientPostDTO DTO contenant les données du client à vérifier.
     * @return true si l'un des champs est null, false si tous les champs sont non-null.
     */
    public boolean lambdaChampsVidePost(ClientPostDTO clientPostDTO) {
        return Stream.of(clientPostDTO.getCompanyName(), clientPostDTO.getFirstName(), clientPostDTO.getLastName(),
                        clientPostDTO.getEmail(), clientPostDTO.getPhoneNumber(), clientPostDTO.getAddress(),
                        clientPostDTO.getZipCode(), clientPostDTO.getCity(), clientPostDTO.getCountry(),
                        clientPostDTO.getState())
                .anyMatch(Objects::isNull);
    }





    // 4) ************************************** EXEMPLE AVEC LA METHODE getAllClient de ClientService ***********************************

    /**
     * Récupère tous les clients de la base de données et les transforme en DTO.
     *
     * @return Une liste de ClientGetDTO. Si aucun client n'est trouvé, une liste vide est retournée.
     */
    public List<ClientGetDTO> getAllClient(){
        List<Client> entities = clientRepository.findAll();
        List<ClientGetDTO> dtos = new ArrayList<>();
        for(Client entity : entities)
            dtos.add(ClientGetMapper.entityToDto(entity));
        return dtos;
    }

    /**
     * Utilisation de 'Stream' pour traiter de manière fonctionnelle les entités Client récupérées de la bdd.
     *
     * Après avoir récupéré toutes les entités Client avec 'clientRepository.findAll()', la méthode les transforme en un flux (stream).
     * Chaque entité Client est ensuite convertie en un objet ClientGetDTO grâce à la référence de méthode 'ClientGetMapper::entityToDto'.
     * Permet d'améliorer la lisibilité du code.
     * Enfin, le flux est collecté dans une liste de ClientGetDTO à l'aide de `Collectors.toList()`.
     *
     * @return Une liste de ClientGetDTO, chacun représentant un client. Si aucun client n'est trouvé, une liste vide est retournée.
     */
    public List<ClientGetDTO> lambdaGetAllClient() {
        return clientRepository.findAll().stream()
                .map(ClientGetMapper::entityToDto)
                .collect(Collectors.toList());
    }





    // 5) ************************************** EXEMPLE AVEC LA METHODE deleteClientById de ClientService ***********************************


    /**
     * Supprime un client de la base de donnée
     * @param id
     * @return
     */
    public Client deleteClientById(Integer id) {
        Optional<Client> find = clientRepository.findById(id);
        if (find.isPresent()) {
            Client client = find.get();
            clientRepository.deleteById(client.getId());
            return client;
        }
        return null;
    }

    /**
     * Au lieu d'utiliser une vérification avec 'isPresent', cette méthode utilise 'map' pour appliquer une opération
     * sur le 'Client' trouvé si celui-ci est présent. À l'intérieur de la lambda passée à 'map', le client est supprimé de la base
     * de données en utilisant 'deleteById'. La lambda retourne ensuite le client supprimé.
     *
     * Si le client n'est pas trouvé (si l'Optional est vide), 'orElse(null)' est utilisé pour retourner 'null'.
     *
     * @param id L'identifiant du client à supprimer.
     * @return Le client supprimé si présent et supprimé avec succès, ou null si aucun client n'est trouvé avec l'id fourni.
     */
    public Client lambdaDeleteClientById(Integer id) {
        return clientRepository.findById(id)
                .map(client -> {
                    clientRepository.deleteById(client.getId());
                    return client;
                }).orElse(null);
    }
}
