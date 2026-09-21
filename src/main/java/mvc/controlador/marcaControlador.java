package mvc.controlador;

import mvc.dao.MarcaDAO;
import mvc.modelo.Marca;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/marcas")
public class marcaControlador {

    private MarcaDAO marcaDAO = new MarcaDAO();

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("marcas", marcaDAO.listarTodos());
        if (!model.containsAttribute("marca")) {
            model.addAttribute("marca", new Marca());
        }
        return "marcas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("marcas", marcaDAO.listarTodos());
        model.addAttribute("marca", marcaDAO.buscarPorId(id));
        return "marcas";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Marca marca, RedirectAttributes ra) {
        boolean ok = marca.getId_marca() == 0 
                 ? marcaDAO.insertar(marca) 
                 : marcaDAO.actualizar(marca);
        if (!ok) {
            ra.addFlashAttribute("error", "Error al guardar la marca.");
        } else {
            ra.addFlashAttribute("exito", "Marca guardada con éxito.");
        }
        return "redirect:/marcas";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id, RedirectAttributes ra) {
        Marca m = new Marca();
        m.setId_marca(id);
        boolean ok = marcaDAO.eliminar(m);
        if (!ok) {
            ra.addFlashAttribute("error", "No se puede eliminar la marca (puede tener productos asociados).");
        }
        return "redirect:/marcas";
    }
}