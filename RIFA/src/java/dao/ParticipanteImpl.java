package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Participante;

public class ParticipanteImpl extends Conexion implements ICRUD<Participante> {

    @Override
    public void registrar(Participante par) throws Exception {
        String SQL = "INSERT INTO PARTICIPANTE (NOMPAR, APEPAR, CELPAR, ESTPAR) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setString(1, par.getNombre());
            ps.setString(2, par.getApellido());
            ps.setString(3, par.getCelular());
            ps.setString(4, par.getEstado());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("error en registrar en participante" + e);
        }

    }

    @Override
    public void modificar(Participante par) throws Exception {
        String SQL = "UPDATE PARTICIPANTE SET (NOMPAR=?, APEPAR=?, CELPAR=?) WHERE IDPAR = ?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(SQL);
            ps.setString(1, par.getNombre());
            ps.setString(2, par.getApellido());
            ps.setString(3, par.getCelular());
            ps.setString(4, par.getEstado());
            ps.setInt(5, par.getIde());
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

}
