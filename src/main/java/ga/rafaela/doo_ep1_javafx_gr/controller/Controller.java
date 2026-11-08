package ga.rafaela.doo_ep1_javafx_gr.controller;

import ga.rafaela.doo_ep1_javafx_gr.dao.CursoDAO;
import ga.rafaela.doo_ep1_javafx_gr.dao.GsonCursoDAO;
import ga.rafaela.doo_ep1_javafx_gr.dao.PersistenciaException;
import ga.rafaela.doo_ep1_javafx_gr.model.ConDiploma;
import ga.rafaela.doo_ep1_javafx_gr.model.Curso;
import ga.rafaela.doo_ep1_javafx_gr.model.CursoCertificado;
import ga.rafaela.doo_ep1_javafx_gr.model.CursoLibre;
import ga.rafaela.doo_ep1_javafx_gr.repository.CursoRepository;
import ga.rafaela.doo_ep1_javafx_gr.repository.Repository;
import javafx.beans.property.ReadOnlyMapProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    // TABLA
    @FXML private TableView<Curso> tablaCursos;
    @FXML private TableColumn<Curso, String> colTipo;
    @FXML private TableColumn<Curso, String> colCodigo;
    @FXML private TableColumn<Curso, Integer> colDuracion;
    @FXML private TableColumn<Curso, Double> colCosto;

    // REGISTRO
    @FXML private ComboBox<String> cmbAcciones;
    @FXML private ComboBox<String> cmbTipoCurso;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtDuracion;
    @FXML private TextField txtCupoMax;
    @FXML private TextField txtInscritos;
    @FXML private TextField txtCertificadora;
    @FXML private CheckBox chbEvaluacionFinalAlDia;
    @FXML private Button btnIr;

    // OTROS
    @FXML private Label lblMensaje;
    @FXML private Button btnEliminar;

    // ATRIBUTOS
    private Repository<Curso> repo;
    private final ObservableList<Curso> cursos = FXCollections.observableArrayList();

    private void iniciarTabla(){
        this.tablaCursos.setItems(this.cursos);
        this.colTipo.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getTipo()));
        this.colCodigo.setCellValueFactory(celda -> new ReadOnlyStringWrapper(celda.getValue().getCodigoCurso()));
        this.colDuracion.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().getDuracionHoras()));
        this.colCosto.setCellValueFactory(celda -> new ReadOnlyObjectWrapper<>(celda.getValue().calcularCosto()));
    }

    public void actualizarListaObservable(){
        cursos.setAll(repo.listar());
    }

    private void limpiarCampos(){
        cmbAcciones.getSelectionModel().clearSelection();
        cmbTipoCurso.getSelectionModel().clearSelection();
        cmbTipoCurso.setDisable(true);
        txtCodigo.clear();
        txtCodigo.setDisable(true);
        txtDuracion.clear();
        txtDuracion.setDisable(true);
        txtCupoMax.clear();
        txtCupoMax.setDisable(true);
        txtInscritos.clear();
        txtInscritos.setDisable(true);
        txtCertificadora.clear();
        txtCertificadora.setDisable(true);
        chbEvaluacionFinalAlDia.setSelected(false);
        chbEvaluacionFinalAlDia.setDisable(true);
        btnIr.setDisable(true);

    }


    @FXML
    private void initialize(){
        // INICIAR TABLA
        iniciarTabla();

        // SET OPCIONES COMBO BOX
        cmbAcciones.getItems().setAll("Registrar","Consultar Evaluación","Emitir Diploma","Buscar por Código");
        cmbTipoCurso.getItems().setAll("Libre","Certificado");

        // CREAR JSONS VACÍOS DE C/ CURSO
        GsonCursoDAO<CursoCertificado> cursosCertificados = new GsonCursoDAO<CursoCertificado>(Path.of("cursos_certificados.json"),CursoCertificado.class);
        GsonCursoDAO<CursoLibre> cursosLibres = new GsonCursoDAO<CursoLibre>(Path.of("cursos_libres.json"),CursoLibre.class);

        // CREAR REPO VACÍO
        this.repo = new CursoRepository(cursosLibres,cursosCertificados);

        // CARGO PLANTAS AL REPO
        try {
            this.repo.cargar();
            actualizarListaObservable();
        }catch (PersistenciaException e){
            lblMensaje.setText("No se pudieron cargar los cursos.");
        }
    }

    @FXML
    private void onAcciones(){
        if(cmbAcciones.getValue() == null){
            return;
        }
        if (cmbAcciones.getValue().equals("Registrar")){
            cmbTipoCurso.setDisable(false);
            txtCodigo.setDisable(true);

            if (cmbTipoCurso.getValue() == null){
                txtCodigo.setDisable(true);
                txtDuracion.setDisable(true);
                txtCupoMax.setDisable(true);
                txtInscritos.setDisable(true);
                txtCertificadora.setDisable(true);
                chbEvaluacionFinalAlDia.setDisable(true);
                btnIr.setDisable(true);
                return;

            }

            if (cmbTipoCurso.getValue().equals("Libre")){
                txtCodigo.setDisable(false);
                txtDuracion.setDisable(false);
                txtCupoMax.setDisable(false);
                txtInscritos.setDisable(false);
                txtCertificadora.setDisable(true);
                chbEvaluacionFinalAlDia.setDisable(true);
                btnIr.setDisable(false);

            }else if (cmbTipoCurso.getValue().equals("Certificado")) {
                txtCodigo.setDisable(false);
                txtDuracion.setDisable(false);
                txtCupoMax.setDisable(false);
                txtInscritos.setDisable(true);
                txtCertificadora.setDisable(false);
                chbEvaluacionFinalAlDia.setDisable(false);
                btnIr.setDisable(false);

            }
        }
        else if (cmbAcciones.getValue().equals("Consultar Evaluación")){
            cmbTipoCurso.setDisable(true);
            txtCodigo.setDisable(false);
            txtDuracion.setDisable(true);
            txtCupoMax.setDisable(true);
            txtInscritos.setDisable(true);
            txtCertificadora.setDisable(true);
            chbEvaluacionFinalAlDia.setDisable(true);
            btnIr.setDisable(false);
        }
        else if (cmbAcciones.getValue().equals("Emitir Diploma")){
            cmbTipoCurso.setDisable(true);
            txtCodigo.setDisable(false);
            txtDuracion.setDisable(true);
            txtCupoMax.setDisable(true);
            txtInscritos.setDisable(true);
            txtCertificadora.setDisable(true);
            chbEvaluacionFinalAlDia.setDisable(true);
            btnIr.setDisable(false);
        }
        else if (cmbAcciones.getValue().equals("Buscar por Código")){
            cmbTipoCurso.setDisable(true);
            txtCodigo.setDisable(false);
            txtDuracion.setDisable(true);
            txtCupoMax.setDisable(true);
            txtInscritos.setDisable(true);
            txtCertificadora.setDisable(true);
            chbEvaluacionFinalAlDia.setDisable(true);
            btnIr.setDisable(false);
        }
    }

    @FXML
    private void onEliminar() throws PersistenciaException{
        if(tablaCursos.getSelectionModel().getSelectedItem() == null){
            lblMensaje.setText("Debe seleccionar un curso para eliminar.");
            return;
        }

        try{
            Curso seleccion = tablaCursos.getSelectionModel().getSelectedItem();

            this.repo.eliminar(seleccion);
            actualizarListaObservable();
            lblMensaje.setText("¡Curso eliminado exitosamente!");
        }catch (PersistenciaException e){
            lblMensaje.setText("Error al eliminar el curso.");
        }
    }

    @FXML
    private void onIr() throws PersistenciaException{

        if (cmbAcciones.getValue().equals("Registrar")){
            try{
                Curso newCurso = null;

                if (cmbTipoCurso.getValue().equals("Libre")) {

                    if(txtCodigo.getText().isEmpty() || txtDuracion.getText().isEmpty() || txtCupoMax.getText().isEmpty() || txtInscritos.getText().isEmpty()){
                        lblMensaje.setText("Debe rellenar todos los campos.");
                        return;
                    }

                    newCurso = new CursoLibre(txtCodigo.getText(), Integer.parseInt(txtDuracion.getText()), Integer.parseInt(txtCupoMax.getText()), Integer.parseInt(txtInscritos.getText()));

                } else if (cmbTipoCurso.getValue().equals("Certificado")) {

                    if(txtCodigo.getText().isEmpty() || txtDuracion.getText().isEmpty() || txtCupoMax.getText().isEmpty() || txtCertificadora.getText().isEmpty()){
                        lblMensaje.setText("Debe rellenar todos los campos.");
                        return;
                    }

                    newCurso = new CursoCertificado(txtCodigo.getText(), Integer.parseInt(txtDuracion.getText()), Integer.parseInt(txtCupoMax.getText()), txtCertificadora.getText(), chbEvaluacionFinalAlDia.isSelected());
                }

                repo.agregar(newCurso);
                actualizarListaObservable();
                limpiarCampos();
                lblMensaje.setText("¡Curso registrado exitosamente!");

            }catch(NumberFormatException e){
                lblMensaje.setText("Debes ingresar números enteros en los campos.");
            }catch(IllegalArgumentException e){
                lblMensaje.setText(e.getMessage());
            }catch (PersistenciaException e){
                e.printStackTrace();
                lblMensaje.setText("No se puedo registrar el curso."+e.getMessage());
            }

        }
        else if (cmbAcciones.getValue().equals("Consultar Evaluación")){
            List<Curso> resultadosBusqueda = buscarCurso(txtCodigo.getText());

            if (resultadosBusqueda.isEmpty()){
                lblMensaje.setText("No se encontró el curso.");

            }else{
                for (Curso c : resultadosBusqueda){

                    if (c instanceof CursoCertificado){

                        if (((CursoCertificado) c).isEvaluacionFinalAlDia()){
                            lblMensaje.setText("Evaluación Final al Día: Sí");

                        }else{
                            lblMensaje.setText("Evaluación Final al Día: No");
                        }

                    }else{
                        lblMensaje.setText(c.getCodigoCurso()+"es un Curso Libre, no aplica una evaluación final.");
                    }
                }
            }

        }
        else if (cmbAcciones.getValue().equals("Emitir Diploma")){
            List<Curso> resultadosBusqueda = buscarCurso(txtCodigo.getText());

            if (resultadosBusqueda.isEmpty()){
                lblMensaje.setText("No se encontró el curso.");
            }else{
                for (Curso c : resultadosBusqueda){
                    if (c instanceof CursoCertificado){
                        ((ConDiploma) c).emitirDiploma();
                        lblMensaje.setText("Diploma emitido con éxito.");
                    }else{
                        lblMensaje.setText(c.getCodigoCurso()+"es un Curso Libre, no emite diplomas.");
                    }
                }
            }

        }
        else if (cmbAcciones.getValue().equals("Buscar por Código")){
            List<Curso> resultadosBusqueda = buscarCurso(txtCodigo.getText());

            if (resultadosBusqueda.isEmpty()){
                lblMensaje.setText("No se encontró el curso.");

            }else{
                for (Curso c : resultadosBusqueda){
                    lblMensaje.setText(c.toString());
                }
            }

        }

    }


    public List<Curso> buscarCurso(String codigo){
        List<Curso> resultadosBusqueda = new ArrayList<Curso>();

        for (Curso c: this.cursos){
            if (c.getCodigoCurso().equals(codigo)){
                resultadosBusqueda.add(c);
            }
        }
        return resultadosBusqueda;
    }

}
