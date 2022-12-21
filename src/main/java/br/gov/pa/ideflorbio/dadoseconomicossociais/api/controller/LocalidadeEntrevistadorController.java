package br.gov.pa.ideflorbio.dadoseconomicossociais.api.controller;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.pa.ideflorbio.dadoseconomicossociais.api.model.EntrevistadorDTO;
import br.gov.pa.ideflorbio.dadoseconomicossociais.domain.service.LocalidadeService;



@RestController
@RequestMapping("/localidade/{id}/entrevistadores")
public class LocalidadeEntrevistadorController {

	@Autowired
	LocalidadeService localidadesCadastro;

	@GetMapping
	public List<EntrevistadorDTO> listar(@PathVariable Long id){
		
		return localidadesCadastro.listarEntrevistadores(id);
	}
	
	@PutMapping("/{idEntrevistador}")
	public void associarLocalidadeEntrevistador(@PathVariable Long id, @PathVariable Long idEntrevistador) {
		localidadesCadastro.vincularEntrevistador(id, idEntrevistador);
	}
	
	@DeleteMapping("/{idEntrevistador}")
	public void desassociarLocalidadeEntrevistador(@PathVariable Long id, @PathVariable Long idEntrevistador) {
		localidadesCadastro.desvincularEntrevistador(id, idEntrevistador);
	}
	
	
}
