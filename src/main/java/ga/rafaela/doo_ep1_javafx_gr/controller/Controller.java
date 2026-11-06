package ga.rafaela.doo_ep1_javafx_gr.controller;

import ga.rafaela.doo_ep1_javafx_gr.model.Curso;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    // TABLA
    @FXML private TableView<Curso> tablaCursos;
    @FXML private TableColumn<Curso, String> colTipo;
    @FXML private TableColumn<Curso, String> colCodigo;
    @FXML private TableColumn<Curso, Integer> colDuracion;
    @FXML private TableColumn<Curso, Integer> colCosto;

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

    @FXML
    private void initialize(){
        cmbAcciones.getItems().setAll("Registrar","Consultar Evaluación","Emitir Diploma","Buscar por Código");
        cmbTipoCurso.getItems().setAll("Libre","Certificado");
    }

    @FXML
    private void onAcciones(){
        if (cmbAcciones.getValue().equals("Registrar")){
            lblMensaje.setText("Registrar");

            cmbTipoCurso.setDisable(false);
            txtCodigo.setDisable(true);

            if (cmbTipoCurso.getValue().equals("Libre")){
                txtCodigo.setDisable(false);
                txtDuracion.setDisable(false);
                txtCupoMax.setDisable(false);
                txtInscritos.setDisable(false);
                txtCertificadora.setDisable(true);
                chbEvaluacionFinalAlDia.setDisable(true);
            }else if (cmbTipoCurso.getValue().equals("Certificado")) {
                txtCodigo.setDisable(false);
                txtDuracion.setDisable(false);
                txtCupoMax.setDisable(false);
                txtInscritos.setDisable(true);
                txtCertificadora.setDisable(false);
                chbEvaluacionFinalAlDia.setDisable(false);
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
            lblMensaje.setText("Evaluacion");
        }
        else if (cmbAcciones.getValue().equals("Emitir Diploma")){
            cmbTipoCurso.setDisable(true);
            txtCodigo.setDisable(false);
            txtDuracion.setDisable(true);
            txtCupoMax.setDisable(true);
            txtInscritos.setDisable(true);
            txtCertificadora.setDisable(true);
            chbEvaluacionFinalAlDia.setDisable(true);
            lblMensaje.setText("Diploma");
        }
        else if (cmbAcciones.getValue().equals("Buscar por Código")){
            cmbTipoCurso.setDisable(true);
            txtCodigo.setDisable(false);
            txtDuracion.setDisable(true);
            txtCupoMax.setDisable(true);
            txtInscritos.setDisable(true);
            txtCertificadora.setDisable(true);
            chbEvaluacionFinalAlDia.setDisable(true);
            lblMensaje.setText("Buscar");
        }
    }

    @FXML
    private void onIr(){

    }

    @FXML
    private void onEliminar(){
        if(tablaCursos.getSelectionModel().getSelectedItem() == null){
            lblMensaje.setText("Debe seleccionar un curso para eliminar.");
        }

    }

    private void limpiarCampos(){
        cmbAcciones.setValue(null);
        cmbTipoCurso.setValue(null);
        txtCodigo.clear();
        txtDuracion.clear();
        txtCupoMax.clear();
        txtInscritos.clear();
        txtCertificadora.clear();
        chbEvaluacionFinalAlDia.setSelected(false);
    }

}
