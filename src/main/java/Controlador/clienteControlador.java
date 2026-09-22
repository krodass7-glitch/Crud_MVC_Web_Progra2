package Controlador;

import org.springframework.ui.Model;

import dao.ClienteDAO;

@Controller
@requestMapping("/cliente")
public class clienteControlador {
    private ClienteDAO clienteDAO = new ClienteDAO();

    @getMapping()
    public String listar(Model model) {
        model.addAttribute("clientes", clienteDAO.listarTodos());
        if(!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }

        return "cliente";
    }

    @getMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
            model.addAttribute("clientes", clienteDAO.listarTodos());
            model.addAttribute("cliente", clienteDAO.buscarPorId(id));

            return "cliente";
    }

    @postMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente, Model model) {
        clienteDAO.guardar(cliente);
        return "redirect:/cliente";
    }

}