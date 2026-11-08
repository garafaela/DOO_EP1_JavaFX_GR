package ga.rafaela.doo_ep1_javafx_gr.model;

public class CursoLibre extends Curso{
    // ATRIBUTOS
    private int cantidadInscritos;

    // CONSTRUCTOR
    public CursoLibre(String codigoCurso, int duracionHoras, int cupoMaximo, int cantidadInscritos){
        super(codigoCurso, duracionHoras, cupoMaximo);
        // de acuerdo al enunciado, "los constructores deben delegar la asignación de valores a los setter, de modo que las validaciones se apliquen desde la construcción del objeto"
        setCantidadInscritos(cantidadInscritos);
    }


    // GETTERS Y SETTERS
    public int getCantidadInscritos() {
        return cantidadInscritos;
    }
    public void setCantidadInscritos(int cantidadInscritos) throws IllegalArgumentException {
        // se agregó una validación adicional a los solicitados, que los inscritos no sea un número negativo
        if  (0 <= cantidadInscritos) {
            this.cantidadInscritos = cantidadInscritos;
        }else{
            throw new IllegalArgumentException("La cantidad de inscritos debe ser un número entero positivo.");
        }
    }

    // MÉTODOS SOBREESCRITOS
    @Override
    public double calcularCosto(){
        double precio = 45000.0; // se toma este dato del enunciado como cuarenta y cinco mil
        if (this.cantidadInscritos > 20){
            precio += precio*0.1;
        }
        return precio;
    }

    @Override
    public String obtenerDetalle(){
        String detalle = "Tipo: Curso Libre || " + super.obtenerDetalle() + " || Cantidad Inscritos: "+ this.cantidadInscritos + " || Costo: $"+ this.calcularCosto();
        return detalle;
    }

    @Override
    public String getTipo(){
        return "Libre";
    }

}