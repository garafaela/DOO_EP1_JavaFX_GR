package ga.rafaela.doo_ep1_javafx_gr.dao;

import ga.rafaela.doo_ep1_javafx_gr.model.Curso;

import java.util.List;

public interface CursoDAO <T extends Curso>{

    // MÉTODOS CARGAR DEL JSON
    public List<T> cargarDAO() throws PersistenciaException;

    //MÉTODOS GUARDAR AL JSON
    public void guardarDAO(List<T> todosCursos) throws PersistenciaException;
}
