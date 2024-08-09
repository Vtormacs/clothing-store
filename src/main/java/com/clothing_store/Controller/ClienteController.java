package com.clothing_store.Controller;

import com.clothing_store.Entity.ClienteEntity;
import com.clothing_store.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<ClienteEntity> save(@RequestBody ClienteEntity clienteEntity){
        try {
            ClienteEntity cliente = this.clienteService.save(clienteEntity);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
