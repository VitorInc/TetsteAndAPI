package com.example.APIrestID.service;

import com.example.APIrestID.entity.Texto;
import com.example.APIrestID.excepts.DidntFind;
import com.example.APIrestID.excepts.DidntRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Service {


    public Service() {
        this.textos = new ArrayList<>();
    }
    public List<Texto> textos(Integer id, String texto){
        List<Texto> filtered;
        if((texto != null) && (id ==null)){
            filtered = this.listOftxts(texto);
        }
        else if((texto == null) && (id != null)){
            filtered = this.listOfIds(id);
        }else{
            filtered = this.textos;
        }
        if(filtered.isEmpty()){
            throw new DidntFind();
        }
        else {
            return filtered;
        }
    }

    public Texto found(final Integer id){
        if(inside(id)){
            return this.textos.get(id.intValue()-1);
        }else {
            throw new DidntFind();
        }
    }

    public void update(Integer id, String texto){
        if(inside(id)){
            updateId(id, texto);
            updateTxt(id,texto);
        }
    }

    public Texto register(final Texto texto){
      texto.setId((int)  this.textos.size()+1);
            try {
    this.textos.add(texto);
    return texto;

    }
            catch (NullPointerException e){
    throw new DidntRegister();
        }
    }

    public Boolean delete(Integer id){
        if(inside(id)){
            this.textos.remove(id.intValue()-1);
            return true;
        }
        return false;
    }

    public List<Texto> txtsString(final String texto){
        return listOftxts(texto);
    }
    public List <Texto> txtsId(final Integer id){
        return listOfIds(id);
    }

    private Boolean inside(Integer id){
        return ((this.textos.size() >= id) && (id>0));
    }

    private List<Texto> textos;

    private List<Texto> listOftxts(String texto){
        return this.textos.stream().
                filter(x -> x.getTexto().contains(texto)).
                collect(Collectors.toList());
    }
    private List<Texto> listOfIds(Integer id){
        return this.textos.stream().
                filter(x -> x.getId().equals(id)).
                collect(Collectors.toList());
    }

    private Boolean updateId(Integer id, String textp){
        if(id != null){
            this.textos.get(id.intValue()-1).setId(id);
        }
        return false;
    }

    private Boolean updateTxt(Integer id, String texto){
        if(texto != null){
            this.textos.get(id.intValue()-1).setTexto(texto);
            return true;
        }
        return false;
    }
}
