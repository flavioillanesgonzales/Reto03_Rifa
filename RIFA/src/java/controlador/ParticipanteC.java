package controlador;

import dao.ParticipanteImpl;
import dao.RifaImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Participante;
import modelo.Rifa;

@Named(value = "participanteC")
@SessionScoped
public class ParticipanteC implements Serializable {

    Participante parc;
    ParticipanteImpl dao;
    List<Participante> listadopar;
    RifaImpl rifa;

    public ParticipanteC() {
        parc = new Participante();
        dao = new ParticipanteImpl();
        listadopar = new ArrayList();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(parc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registro Exitoso"));
            Limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en RegistrarC" + e);
        }
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(parc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Modificación Exitosa"));
            Limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en ModificarC" + e);
        }
    }

    public void listar() throws Exception {
        try {
            listadopar = dao.listarTodos();
            System.out.println("Estoy en listado");
        } catch (Exception e) {
            System.out.println("Error en listarC" + e);
        }
    }

    public void Eliminar(Participante parcs) throws Exception {
        try {
            dao.eliminar(parcs);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Eliminación Exitoso"));
            Limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en EliminarC" + e);
        }
    }

    public List<String> completeTextNombre(String query) throws SQLException, Exception {
        query = parc.getNombre();
        dao.autocompleteNombre(query);
        return dao.autocompleteNombre(query);
    }

    public List<String> completeTextApellido(String query) throws SQLException, Exception {
        query = parc.getApellido();
        dao.autocompleteApellido(query);
        return dao.autocompleteApellido(query);
    }

    public List<String> completeTextCelular(String query) throws SQLException, Exception {
        query = parc.getCelular();
        dao.autocompleteCelular(query);
        return dao.autocompleteCelular(query);
    }

    public void rifaparticipante(int codigo) {
        RifaImpl rif = new RifaImpl();
        rif.RegistrarRifa(codigo);
        System.out.println(parc.getIde() + parc.getNombre() + parc.getApellido());
        System.out.println("COdigo rifaparticipante" + codigo);
    }
    public static int codigo;
    public void validar() throws Exception {
        boolean vali;
        try {
            dao.validarparticipante(parc);
            vali = dao.validacion;
            System.out.println("vali = " + vali + dao.validacion);
            System.out.println(parc.getIde() + parc.getNombre() + parc.getApellido());
            codigo = parc.getIde();
            System.out.println("Codigo" + parc.getIde());
//            rifaparticipante();
//            if (codigo > 0) {
//                rifa.RegistrarRifa(codigo);
//                System.out.println("Estoy en true" +parc.getIde()+ parc.getNombre() + parc.getApellido());
//            } else {
//                registrar();
//            }
            System.out.println("Estoy en Controlador validar");
            System.out.println(vali);
            rifa.codigo = codigo;
            System.out.println("rifa codigo"+rifa.codigo);
            rifaparticipante(codigo);
            Limpiar();
            listar();
         }catch (Exception e) {
            System.out.println("Error en Controlador validar" + e.getMessage());
        }
            
    }

    public void Limpiar() {
        parc = new Participante();
    }

    public Participante getParc() {
        return parc;
    }

    public void setParc(Participante parc) {
        this.parc = parc;
    }

    public ParticipanteImpl getDao() {
        return dao;
    }

    public void setDao(ParticipanteImpl dao) {
        this.dao = dao;
    }

    public List<Participante> getListadopar() {
        return listadopar;
    }

    public void setListadopar(List<Participante> listadopar) {
        this.listadopar = listadopar;
    }

}
