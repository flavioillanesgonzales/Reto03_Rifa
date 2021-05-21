/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Participante;
import modelo.Rifa;


public class RifaImpl extends Conexion implements ICRUD<Rifa>{
    Rifa rifa;
    Participante parti;
    

    public String obtener_id(String nombre){
//        String id = 
         
        return nombre;
    }
    public static int codigo;
    public void RegistrarRifa(int codigo) {
        int cantidad = Integer.parseInt(parti.getCantidad());
        System.out.println("Estoy en rifa Impl, cantidad " + cantidad);
        System.out.println("Estoy en rifa Impl, codigo " + codigo);
    while (cantidad != 0) {
        String SQL = "INSERT INTO RIFA (IDPAR) VALUES (?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setString(1,String.valueOf(codigo));
            ps.execute();
        ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrarRifa" + e.getMessage());
        }
        cantidad = cantidad - 1;
        System.out.println(" " + cantidad);
        
        }
    }
    
    
    
    public String obtenerIdParticipante(String cadenaParticipante) throws SQLException, Exception {
        String sql = "select IDPAR FROM PARTICIPANTE WHERE concat(NOMPAR, ',',APEPAR) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaParticipante);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDPAR");
            }
            return rs.getString("IDPAR");
        } catch (Exception e) {
            System.out.println("Error en obtenerIDParticipante " + e.getMessage());
            throw e;
        }
    }
    
    
    
    
    
    
    
    
    
    
    @Override
    public void modificar(Rifa obj) throws Exception {
        
    }

    @Override
    public void eliminar(Rifa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rifa> listarTodos() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrar(Rifa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
