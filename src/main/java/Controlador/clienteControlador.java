package controlador;

import modelo.Cliente;
import dao.ClienteDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/clientes")
public class clienteControlador {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @GetMapping()
    public String listar(Model model) {
        model.addAttribute("clientes", clienteDAO.listarTodos());
        if(!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }

        return "clientes";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
            model.addAttribute("clientes", clienteDAO.listarTodos());
            model.addAttribute("cliente", clienteDAO.buscarPorId(id));

            return "clientes";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente, RedirectAttributes ra) {
        try {
            boolean ok= cliente.getId_cliente()==0 
                     ? clienteDAO.insertar(cliente) 
                     : clienteDAO.actualizar(cliente);
            
            if (ok) {

                ra.addFlashAttribute("error", "Error al momento de almacenar.");
            } 

        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al guardar el cliente: " + e.getMessage());
        }
        return "redirect:/clientes";
    }

    @PostMapping ("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        Cliente cliente = new Cliente();
        cliente.setId_cliente(id);
        clienteDAO.eliminar(cliente);
        return "redirect:/clientes";
    }
}