package com.example.APIrestID.controller;

import com.example.APIrestID.entity.Texto;
import com.example.APIrestID.service.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ti")
public class Controller {

    private Service service;

    List<Texto> textos = new ArrayList<>();

    public Controller() {
        this.service = new Service();
    }

    @GetMapping
    public ResponseEntity<List<Texto>> allTxt(@RequestParam(required = false) String texto,
                                              @RequestParam(value = "id", required = false,defaultValue = "0") Integer id){
        return new ResponseEntity(this.service.textos(id, texto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Texto> findById(@PathVariable("id") Integer id){
        return new ResponseEntity(this.service.found(id),HttpStatus.FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Texto register(@RequestBody()  Texto texto ){
        return this.service.register(texto);
        }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
            @RequestParam (required = false) String texto){
        this.service.update(id, texto);


    }
    @DeleteMapping ("/{id}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public Boolean delete(@PathVariable("id") Integer id){
        return this.service.delete(id);
    }


}
