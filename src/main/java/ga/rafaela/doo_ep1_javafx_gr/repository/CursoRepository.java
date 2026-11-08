package ga.rafaela.doo_ep1_javafx_gr.repository;

import ga.rafaela.doo_ep1_javafx_gr.dao.CursoDAO;
import ga.rafaela.doo_ep1_javafx_gr.dao.PersistenciaException;
import ga.rafaela.doo_ep1_javafx_gr.model.Curso;
import ga.rafaela.doo_ep1_javafx_gr.model.CursoCertificado;
import ga.rafaela.doo_ep1_javafx_gr.model.CursoLibre;

import java.util.ArrayList;
import java.util.List;

public class CursoRepository implements Repository<Curso>{
    // ATRIBUTOS FINAL
    private final CursoDAO<CursoLibre> cursosLibresDAO;
    private final CursoDAO<CursoCertificado> cursosCertificadosDAO;
    private List<Curso> todosCursos = new ArrayList<>();

    // CONSRTUCTOR
    public CursoRepository(CursoDAO<CursoLibre> cursosLibresDAO, CursoDAO<CursoCertificado> cursosCertificadosDAO){
        this.cursosLibresDAO = cursosLibresDAO;
        this.cursosCertificadosDAO = cursosCertificadosDAO;
    }

    // CARGA CURSOS LOCALMENTE USANDO EL DAO
    @Override
    public void cargar() throws PersistenciaException{
        this.todosCursos.clear();
        this.todosCursos.addAll(this.cursosCertificadosDAO.cargarDAO()); // cargarDAO() ES DE LA INTERFAZ CursoDAO porque son objetos de CursoDAO
        this.todosCursos.addAll(this.cursosLibresDAO.cargarDAO()); // cargarDAO() carga contenido del json y retorna una lista
    }

    // GENERA COPIA DE LISTA DE CURSOS LOCALES
    @Override
    public List<Curso> listar(){
        List<Curso> copiaTodosCursos = new ArrayList<Curso>(this.todosCursos);
        return copiaTodosCursos;
    }

    // AGREGAR CURSO A LISTA LOCAL + JSON
    @Override
    public void agregar(Curso newCurso) throws PersistenciaException{
        this.todosCursos.add(newCurso);
        guardarEnJSON();
    }

    // ELIMINAR CURSO DE LISTA LOCAL + JSON
    @Override
    public void eliminar(Curso cursoAEliminar) throws PersistenciaException{
        this.todosCursos.remove(cursoAEliminar);
        guardarEnJSON();
    }

    // MÉTODOS ADICIONAL PARA GUARDAR CURSOS EN JSON A TRAVÉS DEL OBJETO DAO
    public void guardarEnJSON() throws PersistenciaException{
        List<CursoCertificado> listaCertificadosLocal = new ArrayList<>();
        List<CursoLibre> listaLibresLocal = new ArrayList<>();

        for (Curso c : this.todosCursos){
            if(c instanceof CursoCertificado){
                listaCertificadosLocal.add((CursoCertificado) c);
            } else if (c instanceof CursoLibre){
                listaLibresLocal.add((CursoLibre) c);
            }
        }

        cursosCertificadosDAO.guardarDAO(listaCertificadosLocal);
        cursosLibresDAO.guardarDAO(listaLibresLocal);

    }
}
