package mvc.dao;

import java.util.List;

public interface crudDAO <T> {

    boolean insertar(T t);
    boolean actualizar(T t);
    boolean eliminar(T t);
    T buscarPorId(int id);
    List<T> listarTodos();
}
