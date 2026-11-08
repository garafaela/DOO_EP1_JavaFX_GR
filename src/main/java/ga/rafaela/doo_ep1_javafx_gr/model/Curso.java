package ga.rafaela.doo_ep1_javafx_gr.model;

public abstract class Curso {

    // ATRIBUTOS

    protected String codigoCurso;
    protected int duracionHoras;
    protected int cupoMaximo;

    // CONSTRUCTOR
    public Curso(String codigoCurso, int duracionHoras, int cupoMaximo) {
        // de acuerdo al enunciado, "los constructores deben delegar la asignación de valores a los setter, de modo que las validaciones se apliquen desde la construcción del objeto"
        setCodigoCurso(codigoCurso);
        setDuracionHoras(duracionHoras);
        setCupoMaximo(cupoMaximo);
    }

    // GETTERS Y SETTERS
    public String getCodigoCurso() {
        return codigoCurso;
    }
    public void setCodigoCurso(String codigoCurso) throws IllegalArgumentException {
        if (codigoCurso == null || codigoCurso.length() == 0) {
            throw new IllegalArgumentException("El código de curso no puede estar vacío.");
        }else{
            this.codigoCurso = codigoCurso;
        }
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }
    public void setDuracionHoras(int duracionHoras) throws IllegalArgumentException{
        if (4 <= duracionHoras && duracionHoras <= 200) {
            this.duracionHoras = duracionHoras;
        }else{
            throw new IllegalArgumentException("La duración del curso debe ser entre 4 a 200 horas.");
        }
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }
    public void setCupoMaximo(int cupoMaximo) {
        if (0 < cupoMaximo) {
            this.cupoMaximo = cupoMaximo;
        }else{
            throw new IllegalArgumentException("El cupo máximo del curso debe ser mayor que cero.");
        }
    }

    // MÉTODOS ABSTRACTOS
    public abstract double calcularCosto();
    public abstract String getTipo();

    // MÉTODOS ADICIONALES
    @Override
    public String toString() {
        return "Código del Curso: " + this.codigoCurso + " || Duración: " + this.duracionHoras + " hrs";
    }

    public String obtenerDetalle(){
        String detalle = this.toString() +
                " || Cupo Máximo: "+this.cupoMaximo;
        return detalle;
    }

}
