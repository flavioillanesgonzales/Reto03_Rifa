package services;

import java.io.Serializable;


public class service implements Serializable {

    public int numero;
    public int Randon(int codigo) throws Exception {
        try {
            int cod;
            numero = (int) (Math.random() * codigo + 1000);
            System.out.println("El número aleatorio es:" + numero);
            cod = numero;
            System.out.println("El cod es " + cod);
        } catch (Exception e) {
            System.out.println("Error en servicioRandom" + e.getMessage());
        }
        return numero;
    }

}
