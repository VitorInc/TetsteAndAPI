package com.example.APIrestID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ti")
public class Controller {
    List<Texto> textos = new ArrayList<>();

    public Controller() {
        Texto txt1 = new Texto(1,"deu" );

        textos.add(txt1);
    }

    @GetMapping
    public Object getTxt(@RequestParam(required = false) String texto){
        return textos;
    }

    @GetMapping("/{id}")
    public Texto findById(@PathVariable("id") Integer id){
        return this.textos.stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public ResponseEntity<Integer> postTxt(@RequestBody() final Texto texto ){
        if(texto.getId() == null){
            texto.setId(textos.size()+1);
        }
        textos.add(texto);
        return new ResponseEntity<> (texto.getId(), HttpStatus.CREATED);

    }
    @PutMapping
    public ResponseEntity putTxt(@RequestBody final Texto texto){
        textos.stream().
                filter(x -> x.getId().
                        equals(texto.getId())).
                forEach(x -> x.setTexto(texto.getTexto()));

        return new ResponseEntity (HttpStatus.NO_CONTENT);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity deleteTxt(@PathVariable("id") Integer id){
        textos.removeIf(x -> x.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}
