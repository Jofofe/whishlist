package com.jofofe.whishlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.entity.Client;
import com.jofofe.whishlist.exception.ClientNotFoundException;
import com.jofofe.whishlist.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClientService extends AbstractService<Client, String, ClientRepository> {

    private final ObjectMapper mapper;

    ClientService(ClientRepository repository, ObjectMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    public List<ClientDTO> findClients() {
        List<Client> clients = repository.findAll();
        return clients.stream().map(client -> mapper.convertValue(client, ClientDTO.class)).collect(Collectors.toList());
    }

    public ClientDTO findClient(String idClient) {
        Client client = findClientById(idClient).orElseThrow(ClientNotFoundException::new);
        return mapper.convertValue(client, ClientDTO.class);
    }

    public Optional<Client> findClientById(String id) {
        return repository.findById(id);
    }
}
