package com.jofofe.whishlist.controller;

import com.jofofe.whishlist.dto.ProductDTO;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.dto.ClientDTO;
import com.jofofe.whishlist.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client")
@Api(value = "Client")
public class ClientController {
    
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @ApiOperation(value = "Retornar todos os clients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Clients retornados com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<List<ClientDTO>> findClients() {
        List<ClientDTO> clientsDTO = clientService.findClients();
        return ResponseEntity.ok(clientsDTO);
    }
    
    @GetMapping("/{idClient}")
    @ApiOperation(value = "Retornar client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Client retornado com sucesso", response = Object.class),
            @ApiResponse(code = 404, message = "Alguma informação não foi encontrada"),
            @ApiResponse(code = 400, message = "Ocorreu algum erro negocial"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    public ResponseEntity<ClientDTO> findClient(@PathVariable String idClient) {
        ClientDTO clients = clientService.findClient(idClient);
        return ResponseEntity.ok(clients);
    }
    
}
