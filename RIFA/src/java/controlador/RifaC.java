
package controlador;

import dao.Conexion;
import dao.ParticipanteImpl;
import dao.RifaImpl;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import modelo.Rifa;
import services.service;

@Named (value="rifaC")
@SessionScoped


public class RifaC extends Conexion implements Serializable {


    
    private Rifa rifa;
    private service ser;
    private List<String> images;
    private RifaImpl dao;
    private static int obtencion;
    

    
    public static void Jugada(){
        service sers = new service();
        sers.Randon();
        obtencion = sers.numero;
    }
    
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            images.add("nature" + i + ".jpg");
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
}
