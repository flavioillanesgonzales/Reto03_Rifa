package controlador;

import dao.Conexion;
import dao.RifaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Participante;
import modelo.Rifa;
import services.service;

@Named(value = "rifaC")
@SessionScoped

public class RifaC extends Conexion implements Serializable {

    private Rifa rifa;
    private service ser;
    private List<String> images;
    private RifaImpl dao;
    private List<Rifa> listadorif;
    private List jugar;
    private Participante par;
    String ganador;

    public RifaC() {
        jugar = new ArrayList();
        rifa = new Rifa();
        dao = new RifaImpl();
        listadorif = new ArrayList();
        ser = new service();
        par = new Participante();
    }

    public void jugada() {
        try {
            dao = new RifaImpl();
            int numero;
            numero = listadorif.size();
            ser.Randon(numero);
            numero = ser.numero;
            System.out.println("Numeroo" + numero);
            
            
            rifa.setCODRIF(String.valueOf(numero));
            dao.Ganador(rifa.getCODRIF()); 
            ganador = dao.getResultado();
            rifa.setRESULTADO(ganador);
            System.out.println("Este es resultado en controler" + ganador);
        } catch (Exception e) {
            System.out.println("Error en" + e.getMessage());
        }
    }

    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
        }
    }
    
    public void eliminar(){
        try {
            dao.eliminar(rifa);
        } catch (Exception e) {
            System.out.println("Error en" + e.getMessage());
        }
    }
    
    public void listarRifa() throws Exception {
        try {
            listadorif = dao.listarTodos();
        } catch (Exception e) {
            System.out.println("Error en controlador listar " + e.getMessage());
        }
    }

    public RifaImpl getDao() {
        return dao;
    }

    public void setDao(RifaImpl dao) {
        this.dao = dao;
    }

    public List<String> getImages() {
        return images;
    }

    public Rifa getRifa() {
        return rifa;
    }

    public void setRifa(Rifa rifa) {
        this.rifa = rifa;
    }

    public service getSer() {
        return ser;
    }

    public void setSer(service ser) {
        this.ser = ser;
    }

    public List<Rifa> getListadorif() {
        return listadorif;
    }

    public void setListadorif(List<Rifa> listadorif) {
        this.listadorif = listadorif;
    }
}
