package vendas.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    
	@GetMapping
	@RequestMapping(value = "hello/cli/{nome}")
	public String helloCli(@PathVariable("nome")String nome) {
		return String.format("olaaaa", nome);
	}
}