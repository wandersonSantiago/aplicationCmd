package com.cmd.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cmd.model.Comando;
import com.cmd.service.ComandoService;

@RestController
@RequestMapping("/cmd")
public class ComandoResource {

	@Autowired
	private ComandoService comandoService;
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/executar")
	public Comando executar(@RequestBody Comando comando) {
		return comandoService.executar(comando);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/host")
	public Comando host(@RequestBody Comando comando) {
		Comando c = comandoService.executar(comando);
		c.setHost(c.getRetorno().get(0));
		return c;
	}
}
