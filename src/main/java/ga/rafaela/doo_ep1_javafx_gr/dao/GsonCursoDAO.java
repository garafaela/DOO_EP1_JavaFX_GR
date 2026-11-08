package ga.rafaela.doo_ep1_javafx_gr.dao;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import ga.rafaela.doo_ep1_javafx_gr.model.Curso;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

public class GsonCursoDAO <T extends Curso> implements CursoDAO<T>{
    // ATRIBUTOS FINAL
    private final Path nombreArchivo;
    private final Type tipoLista;
    private final Gson gson = new Gson();

    // CONSTRUCTOR
    public GsonCursoDAO(Path nombreArchivo, Class<T> clase){
        this.nombreArchivo = nombreArchivo;
        this.tipoLista = TypeToken.getParameterized(List.class, clase).getType();

    }

    // MÉTODOS CARGAR DEL JSON
    @Override
    public List<T> cargarDAO() throws PersistenciaException{

        if (Files.notExists(nombreArchivo)){
            return new ArrayList<>();
        }

        try(Reader reader = Files.newBufferedReader(nombreArchivo, StandardCharsets.UTF_8)){
            List<T> todosCursosJson = gson.fromJson(reader, this.tipoLista);

            if (todosCursosJson == null){
                return new ArrayList<>();
            }

            if (todosCursosJson.contains(null)){
                throw new PersistenciaException("No se pudo leer el archivo porque contiene un elemento vacío");
            }

            return new ArrayList<T>(todosCursosJson);
        }catch(IOException | JsonParseException e){
            throw new PersistenciaException("No se pudo cargar el archivo.",e);
        }

    }

    //MÉTODOS GUARDAR AL JSON
    @Override
    public void guardarDAO(List<T> todosCursos) throws PersistenciaException{
        try(Writer writer = Files.newBufferedWriter(nombreArchivo, StandardCharsets.UTF_8)){
            this.gson.toJson(todosCursos, writer);
        }catch (IOException | JsonParseException e){
            throw new PersistenciaException("No se pudo guardar el archivo.",e);
        }
    }

}
