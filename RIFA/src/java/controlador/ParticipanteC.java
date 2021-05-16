
package controlador;

import dao.ParticipanteImpl;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Participante;


@Named(value="PartiC")
@SessionScoped
public class ParticipanteC implements Serializable{
    private Participante parc = new Participante();
    private ParticipanteImpl dao = new ParticipanteImpl();
    private List<Participante> listado;
    
    public void Registrar() throws Exception{
            try {
                dao.registrar(parc);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK","Registro Exitoso"));
                Limpiar();
                Listar();
            } catch (Exception e) {
                System.out.println("Error en RegistrarC" + e);
            }
    }
    
    public void Modificar() throws Exception{
            try {
                dao.modificar(parc);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK","Modificación Exitoso"));
                Limpiar();
                Listar();
            } catch (Exception e) {
                System.out.println("Error en ModicarC" + e);
            }
    }
    
    public void Eliminar() throws Exception{
            try {
                dao.eliminar(parc);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "OK","Eliminación Exitoso"));
                Limpiar();
                Listar();
            } catch (Exception e) {
                System.out.println("Error en EliminarC" + e);
            }
    }
    
    
    public void Listar(){
            try {
                listado = dao.listarTodos();
            } catch (Exception e) {
                System.out.println("Error en ListarC" + e);
            }
    }
    
    
    public void Limpiar(){
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

    public List<Participante> getListado() {
        return listado;
    }

    public void setListado(List<Participante> listado) {
        this.listado = listado;
    }
    
    
    
}
