package ga.rafaela.doo_ep1_javafx_gr.repository;

import ga.rafaela.doo_ep1_javafx_gr.dao.PersistenciaException;
import ga.rafaela.doo_ep1_javafx_gr.model.Curso;

import java.util.List;

public interface Repository<Curso> {

    // CARGA CURSOS LOCALMENTE USANDO EL DAO
    void cargar() throws PersistenciaException;

    // GENERA LISTA DE CURSOS YA CARGADOS
    List<Curso> listar();

    // AGREGAR CURSO A LISTA LOCAL
    void agregar(Curso newCurso) throws PersistenciaException;

    // ELIMINAR CURSO DE LISTA LOCAL
    void eliminar(Curso cursoAEliminar) throws PersistenciaException;
}
