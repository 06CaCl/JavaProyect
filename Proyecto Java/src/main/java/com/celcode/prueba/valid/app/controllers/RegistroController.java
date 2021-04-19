package com.celcode.prueba.valid.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.celcode.prueba.valid.app.models.dto.RegistroDto;
import com.celcode.prueba.valid.app.models.entity.Registro;
import com.celcode.prueba.valid.app.models.service.IRegistroService;

@Controller
@SessionAttributes("registro")
public class RegistroController {
	

	private static final String REDIRECT_LISTAR = "redirect:/listar";
	
	@Autowired
	private IRegistroService registroService;
	
	@GetMapping(path = {"/listar", "", "/"})
	public String listarRegistros(Map<String, Object> modelMap) {
		List<Registro> listRegistros = registroService.findAll();
		modelMap.put("registros", listRegistros);
		return "listarRegistro";
	}
	
	@PostMapping(path = "/crear")
	public String saveRegistro(@Valid @ModelAttribute("registro") RegistroDto registro, BindingResult result,
			Map<String, Object> modelMap, RedirectAttributes flash, SessionStatus status) {
	
		if(result.hasErrors()) {
			return "formRegistro";
		}
		Registro registroDb = new Registro();
		registroDb.setNombre(registro.getNombre());
		registroDb.setApellido(registro.getApellido());
		registroDb.setProcesado(false);
		registroService.saveRegistro(registroDb);
		status.setComplete();
		flash.addFlashAttribute("success", "Registro creado");
		return REDIRECT_LISTAR;
	}
	
	@GetMapping(path = "/listar/{id}")
	public String procesarRegistro(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		Registro registro = null;
		if(id > 0) {
			registro = registroService.findById(id);
			if(registro == null) {
				flash.addFlashAttribute("error", "El registro con el id solicitado no existe");
				return REDIRECT_LISTAR;
			}
			if(registro.isProcesado()) {
				flash.addFlashAttribute("info", "El registro ya ha sido procesado");
				return REDIRECT_LISTAR;		
				
			}else {
				registro.setProcesado(true);
				registroService.saveRegistro(registro);
				flash.addFlashAttribute("success", "");
				flash.addFlashAttribute("success", "Registro actualizado");	
				return REDIRECT_LISTAR;						
			}	
		}else {
			flash.addFlashAttribute("error", "Id inválido");
			return REDIRECT_LISTAR;
			
		}
	}
	
	@GetMapping(path = "/formRegistro")
	public String crear(Map<String, Object> model) {
		RegistroDto registro = new RegistroDto();
		model.put("registro", registro);
		return "formRegistro";
	}

}
