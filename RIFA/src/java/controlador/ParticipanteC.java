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
    private RifaImpl rifa = new RifaImpl();

    public ParticipanteC() {
        parc = new Participante();
        dao = new ParticipanteImpl();
        listadopar = new ArrayList();
    }

    public void registrar() throws Exception {
        try {
            dao.registrar(parc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Registro de rifa y participante exitosa"));
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
    
    public void validar() throws Exception {
        try {
            dao.validarparticipante(parc);
            int codigo = parc.getIde();
            String cantidad = parc.getCantidad();
            System.out.println("Codigo" + parc.getIde() + "Cantidad" + parc.getCantidad());
            if(codigo > 0){
                rifa.RegistrarRifa(codigo,cantidad);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registro de rifa exitosa"));
            }
            else if(codigo == 0){
                System.out.println ("Estoy aca en else " + parc.getIde() + parc.getNombre() + parc.getApellido() + parc.getCantidad());
                registrar();
                dao.obtenerId(parc);
                codigo = parc.getIde();
                cantidad = parc.getCantidad();
                rifa.RegistrarRifa(codigo,cantidad);
                System.out.println("Este es el id " + parc.getIde());
            }
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

    public RifaImpl getRifa() {
        return rifa;
    }

    public void setRifa(RifaImpl rifa) {
        this.rifa = rifa;
    }

}
