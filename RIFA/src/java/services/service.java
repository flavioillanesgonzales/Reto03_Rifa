package services;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

public class service implements Serializable {

    public static int numero;

    public int Randon(int codigo) throws Exception {
        try {
            numero = (int) (Math.random() * codigo + 1000);
            System.out.println("El número aleatorio es:" + numero);
            System.out.println("Numero numero = " +numero);
        } catch (Exception e) {
            System.out.println("Error en servicioRandom" + e.getMessage());
        }
        return numero;
    }

}
