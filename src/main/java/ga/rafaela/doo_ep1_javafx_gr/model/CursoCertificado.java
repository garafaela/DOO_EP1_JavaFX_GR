package ga.rafaela.doo_ep1_javafx_gr.model;

public class CursoCertificado extends Curso implements ConDiploma {
    // ATRIBUTOS
    private String certificadora;
    private boolean evaluacionFinalAlDia;
    private boolean diplomaEmitido;

    // CONSTRUCTOR 1
    public CursoCertificado(String codigoCurso, int duracionHoras, int cupoMaximo, String certificadora, boolean evaluacionFinalAlDia) {
        super(codigoCurso, duracionHoras, cupoMaximo);
        this.certificadora = certificadora;
        this.evaluacionFinalAlDia = evaluacionFinalAlDia;
        this.diplomaEmitido = false;
    }

    // CONSTRUCTOR 2 COMPLETO (Sobrecarga de métodos constructor)
    public CursoCertificado(String codigoCurso, int duracionHoras, int cupoMaximo, String certificadora, boolean evaluacionFinalAlDia, boolean diplomaEmitido) {
        super(codigoCurso, duracionHoras, cupoMaximo);
        this.certificadora = certificadora;
        this.evaluacionFinalAlDia = evaluacionFinalAlDia;
        this.diplomaEmitido = diplomaEmitido;
    }


    // GETTERS Y SETTERS
    public String getCertificadora() {
        return certificadora;
    }
    public void setCertificadora(String certificadora) {
        this.certificadora = certificadora;
    }

    public boolean isEvaluacionFinalAlDia() {
        return evaluacionFinalAlDia;
    }
    public void setEvaluacionFinalAlDia(boolean evaluacionFinalAlDia) {
        this.evaluacionFinalAlDia = evaluacionFinalAlDia;
    }

    // CORRECCIÓN: Agregué getters y setters privados para mantener encapsulamiento
    private boolean isDiplomaEmitido(){
        return diplomaEmitido;
    }
    private void setDiplomaEmitido(boolean diplomaEmitido){
        this.diplomaEmitido = diplomaEmitido;
    }


    // MÉTODOS SOBREESCRITOS
    @Override
    public double calcularCosto(){
        double precio = 85000.0 ; // se toma este dato del enunciado como ochenta y cinco mil
        if (this.evaluacionFinalAlDia == false){
            precio += precio*0.2;
        }
        return precio;
    }

    @Override
    public String obtenerDetalle(){
        String evAlDia = "No";
        String dipEmit = "No";
        if (this.evaluacionFinalAlDia == true){
            evAlDia = "Sí";
        }
        if (this.diplomaEmitido == true){
            dipEmit = "Sí";
        }

        String detalle = "Tipo: Curso Certificado || "+ super.obtenerDetalle() + " || Entidad Certificadora: "+this.certificadora + " || Evaluacion Final al Día: " +evAlDia+ " || Diploma Emitido: "+dipEmit+ " || Costo: $"+this.calcularCosto();
        return detalle;
    }

    @Override
    public String getTipo(){
        return "Certificado";
    }

    // MÉTODOS INTERFAZ
    // CORRECCIÓN: usé los getter y setter privados, para que sólo se pueda acceder a ellos a través de los métodos de interfaz.
    @Override
    public boolean tieneDiplomaEmitido(){
        return isDiplomaEmitido();
    }

    @Override
    public void emitirDiploma(){
        setDiplomaEmitido(true);
    }



}