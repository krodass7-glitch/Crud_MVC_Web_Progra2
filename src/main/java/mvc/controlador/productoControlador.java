package mvc.controlador;

import mvc.modelo.Producto;
import mvc.dao.ProductoDAO;
import mvc.dao.MarcaDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/productos")
public class productoControlador {

    private ProductoDAO productoDAO = new ProductoDAO();
    private MarcaDAO marcaDAO = new MarcaDAO();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoDAO.listarTodos());
        model.addAttribute("marcas", marcaDAO.listarTodos());
        if(!model.containsAttribute("producto")) {
            model.addAttribute("producto", new Producto());
        }
        return "productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("productos", productoDAO.listarTodos());
        model.addAttribute("marcas", marcaDAO.listarTodos());
        model.addAttribute("producto", productoDAO.buscarPorId(id));
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto, RedirectAttributes ra) {
    try {
        if (producto.getId_marca() <= 0) {
            ra.addFlashAttribute("error", "Por favor, seleccione una marca válida.");
            return "redirect:/productos";
        }

        boolean ok = producto.getId_producto() == 0 
                 ? productoDAO.insertar(producto) 
                 : productoDAO.actualizar(producto);
        
        if (!ok) {
            ra.addFlashAttribute("error", "Error SQL al insertar el producto. Revisa la consola de tu IDE (Output/Console).");
        } else {
            ra.addFlashAttribute("exito", "Producto guardado correctamente.");
        }

    } catch (Exception e) {
        ra.addFlashAttribute("error", "Excepción: " + e.getMessage());
    }
    return "redirect:/productos";
}

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        Producto producto = new Producto();
        producto.setId_producto(id);
        boolean ok = productoDAO.eliminar(producto);
        if (!ok) {
            ra.addFlashAttribute("error", "No se pudo eliminar el producto.");
        }
        return "redirect:/productos";
    }
}