package ga.rafaela.doo_ep1_javafx_gr.dao;

// MANEJA EXCEPCIONES DAO: CARGAR/GUARDAR ARCHIVOS JSON
public class PersistenciaException extends Exception {

    public PersistenciaException(String mensaje, Throwable causa){
        super(mensaje,causa);
    }

    public PersistenciaException(String mensaje){
        super(mensaje);
    }
}
