package com.jofofe.whishlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jofofe.whishlist.WhishlistApplication;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.entity.Client;
import com.jofofe.whishlist.repository.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {WhishlistApplication.class})
public class ClientServiceTest {

    @InjectMocks
    ClientService clientService;

    @Mock
    ClientRepository repository;

    @Mock
    ObjectMapper mapper;

    @Test
    public void findClientsTest() {
        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(createClient()));
        Mockito.when(mapper.convertValue(any(), eq(ClientDTO.class))).thenReturn(createClientDTO());
        clientService.findClients();
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    public void findClientTest() {
        Mockito.when(repository.findById(any())).thenReturn(Optional.of(createClient()));
        Mockito.when(mapper.convertValue(any(), eq(ClientDTO.class))).thenReturn(createClientDTO());
        clientService.findClient("sdfljk3wkj43r5");
        verify(repository, atLeastOnce()).findById(any());
    }

    private Client createClient() {
        return Client.builder()
                .id("q4jM6hL8iRtU9sN2eF7kP3a")
                .email("jojo@gmail.com")
                .name("João")
                .build();
    }

    private ClientDTO createClientDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("lktj34k5n3tjfke");
        return clientDTO;
    }
}
