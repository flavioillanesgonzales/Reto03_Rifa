package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Participante;
import modelo.Rifa;

public class ParticipanteImpl extends Conexion implements ICRUD<Participante> {

    @Override
    public void registrar(Participante par) throws Exception {

        String SQL = "INSERT INTO PARTICIPANTE (NOMPAR, APEPAR, CELPAR, ESTPAR) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setString(1, par.getNombre());
            ps.setString(2, par.getApellido());
            ps.setString(3, par.getCelular());
            ps.setString(4, "A");
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("error en registrar en participante" + e);
        }
    }

    @Override
    public void modificar(Participante par) throws Exception {
        String SQL = "UPDATE PARTICIPANTE SET NOMPAR=?, APEPAR=?, CELPAR=? WHERE IDPAR = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setString(1, par.getNombre());
            ps.setString(2, par.getApellido());
            ps.setString(3, par.getCelular());
            ps.setInt(4, par.getIde());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("error en modificar Participante" + e);
        }
    }

    @Override
    public void eliminar(Participante par) throws Exception {
        String SQL = "UPDATE PARTICIPANTE SET ESTPAR='I' WHERE IDPAR=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setInt(1, par.getIde());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("error en eliminar Participante" + e);
        }

    }

    @Override
    public List listarTodos() throws Exception {
        List<Participante> listado = null;
        Participante pars;
        String SQL = "SELECT * FROM PARTICIPANTE";
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                pars = new Participante();
                pars.setIde(rs.getInt("IDPAR"));
                pars.setNombre(rs.getString("NOMPAR"));
                pars.setApellido(rs.getString("APEPAR"));
                pars.setCelular(rs.getString("CELPAR"));
                pars.setEstado(rs.getString("ESTPAR"));
                listado.add(pars);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("error en lista Participante" + e);
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public List<String> autocompleteNombre(String consulta) throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "select top 2 (NOMPAR) AS NOMBRES from PARTICIPANTE WHERE NOMPAR LIKE " + "'%" + consulta + "%'";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
//            ps.setString(1,consulta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("NOMBRES"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteNombre" + e.getMessage());
        }
        return lista;
    }

    public List<String> autocompleteApellido(String consulta) throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "select top 2 (APEPAR) AS APELLIDOS from PARTICIPANTE WHERE APEPAR LIKE " + "'%" + consulta + "%'";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("APELLIDOS"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteApellido" + e.getMessage());
        }
        return lista;
    }

    public List<String> autocompleteCelular(String consulta) throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "select top 2 (CELPAR) AS CELULARES from PARTICIPANTE WHERE CELPAR LIKE " + "'%" + consulta + "%'";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("CELULARES"));
            }
            System.out.println("Estoy en impl Celular");
            System.out.println("Consulta" + consulta);
            System.out.println("Lista" + lista);
        } catch (Exception e) {
            System.out.println("Error en autocompleteCelular" + e.getMessage());
        }
        return lista;
    }

    public static boolean validacion = false;
    public void validarparticipante(Participante pars) throws Exception {
        String nombre = pars.getNombre();
        String apellido = pars.getApellido();
        String celular = pars.getCelular();
//        String SQL = "SELECT IDPAR FROM PARTICIPANTE WHERE NOMPAR='?'" + " and APEPAR='?'"+ "and CELPAR='?'";
        String SQL = "SELECT IDPAR FROM PARTICIPANTE WHERE NOMPAR='" + nombre + "' and APEPAR='" + apellido + "' and CELPAR='" + celular + "'";
        System.out.println("codigo" + pars.getIde());
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(SQL);
//            par.setIde(rs.getInt("IDPAR"));
//            pars.setIde(Integer.parseInt(rs.getString("IDPAR")));
            pars.setIde(Integer.parseInt("2"));
            System.out.println("codigo" + pars.getIde());
//            ps.setString(1, par.getNombre());
//            ps.setString(2, par.getApellido());
//            ps.setString(3, par.getCelular());
            System.out.println("Nombre" + pars.getNombre() + "Apellido" + pars.getApellido() + "Celular" + pars.getCelular());
            rs.close();
            st.close();
            int val = pars.getIde();
            System.out.println("Val = " + val);
        } catch (Exception e) {
            System.out.println("Error en validar participante" + e.getMessage());
        }
    }
}
