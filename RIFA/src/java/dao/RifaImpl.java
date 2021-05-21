/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controlador.RifaC;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import modelo.Participante;
import modelo.Rifa;



@Named(value="dao")
@SessionScoped
public class RifaImpl extends Conexion implements ICRUD<Rifa> {

    public static String getResultado() {
        return resultado;
    }

    public static void setResultado(String aResultado) {
        resultado = aResultado;
    }

    private Rifa rifa = new Rifa();
    Participante parti = new Participante();

    public String obtener_id(String nombre) {
//        String id =  
        return nombre;
    }

    public void RegistrarRifa(int codigo, String cantidad) {
        System.out.println("Estoy en el metodo prueba" + "codigo" + codigo + "Cantidad" + cantidad);
        int can = Integer.parseInt(cantidad);
        while (can != 0) {
            String SQL = "INSERT INTO RIFA (IDPAR) VALUES (?)";
            try {
                PreparedStatement ps = this.conectar().prepareStatement(SQL);
                ps.setString(1, String.valueOf(codigo));
                ps.execute();
                ps.close();
            } catch (Exception e) {
                System.out.println("Error en registrar rifas" + e.getMessage());
            }
            can = can - 1;
            System.out.println(can);
        }
    }

    private static String resultado;

    public void Ganador(String ganador) {
        try {
            String SQL = "SELECT IDPAR FROM RIFA WHERE IDRIF='" + ganador + "'";
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            try {
                rs.next();
                parti.setIde(rs.getInt("IDPAR"));
                rifa.setCODRIF(ganador);
                rs.close();
                st.close();
                System.out.println("El id de mi participante es" + parti.getIde());
                try {
                    SQL = "SELECT NOMPAR,APEPAR FROM PARTICIPANTE INNER JOIN RIFA ON PARTICIPANTE.IDPAR = RIFA.IDPAR where Rifa.IDRIF='" + ganador + "';";
                    st = this.conectar().createStatement();
                    rs = st.executeQuery(SQL);
                    rs.next();
                    parti.setNombre(rs.getString("NOMPAR"));
                    parti.setApellido(rs.getString("APEPAR"));
                    resultado = ("El ganador es:" + parti.getNombre()+" " + parti.getApellido() + " " + "Con el número de rifa: " + rifa.getCODRIF());
                } catch (Exception e) {
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            System.out.println("Error en generar ganador" + e.getMessage() + "IDPARTICIPANTE" + parti.getIde());
        }

    }

    @Override
    public void modificar(Rifa obj) throws Exception {

    }

    @Override
    public void eliminar(Rifa rif) throws Exception {
        String SQL = "DELETE FROM RIFA WHERE IDRIF = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setString(1, rif.getCODRIF());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en eliminar Rifa" + e);
        }
    }

    @Override
    public List<Rifa> listarTodos() throws Exception {
        List<Rifa> listado = null;
        Rifa rifs;
        String SQL = "SELECT * FROM RIFA inner join PARTICIPANTE ON RIFA.IDPAR = PARTICIPANTE.IDPAR;";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                rifs = new Rifa();
                rifs.setCODRIF(rs.getString("IDRIF"));
                rifs.setIDPAR(rs.getString("IDPAR"));
                rifs.setNOMPAR(rs.getString("NOMPAR"));
                rifs.setAPEPAR(rs.getString("APEPAR"));
                rifs.setCELULAR(rs.getString("CELPAR"));
                listado.add(rifs);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("error en lista Rifas" + e);
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<Rifa> listarCodigos() throws Exception {
        List<Rifa> listado = null;
        Rifa rifs;
        String SQL = "SELECT IDRIF FROM RIFA";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                rifs = new Rifa();
                rifs.setCODRIF(rs.getString("IDRIF"));
                listado.add(rifs);
            }
            rs.close();
            st.close();
            System.out.println("Listado" + listado);
        } catch (Exception e) {
            System.out.println("error en lista Codigos Rifas" + e);
        } finally {
            this.cerrar();
        }
        return listado;
    }

    @Override
    public void registrar(Rifa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Rifa getRifa() {
        return rifa;
    }

    public void setRifa(Rifa rifa) {
        this.rifa = rifa;
    }

}
