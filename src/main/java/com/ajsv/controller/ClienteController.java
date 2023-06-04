package com.ajsv.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ajsv.dto.ClienteDTO;
import com.ajsv.exception.ModeloNotFoundException;
import com.ajsv.model.Cliente;
import com.ajsv.service.IClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService service;
	
	@Autowired
	private ModelMapper mapper;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() throws Exception{				
		List<ClienteDTO> lista = service.listar().stream().map(p -> mapper.map(p, ClienteDTO.class)).collect(Collectors.toList());
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> listarPorId(@PathVariable("id") Integer id) throws Exception{
		ClienteDTO dtoResponse;
		Cliente obj = service.listarPorId(id); //Cliente		

		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}else {
			dtoResponse = mapper.map(obj, ClienteDTO.class); //ClienteDTO
		}
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK); 		
	}	
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody ClienteDTO dtoRequest) throws Exception{
		Cliente p = mapper.map(dtoRequest, Cliente.class);
		Cliente obj = service.registrar(p);
		//ClienteDTO dtoResponse = mapper.map(obj, ClienteDTO.class);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdCliente()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<ClienteDTO> modificar(@RequestBody ClienteDTO dtoRequest) throws Exception {
		Cliente pac = service.listarPorId(dtoRequest.getIdCliente());
		
		if(pac == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + dtoRequest.getIdCliente());	
		}
		
		Cliente p = mapper.map(dtoRequest, Cliente.class);
		 
		Cliente obj = service.modificar(p);
		
		ClienteDTO dtoResponse = mapper.map(obj, ClienteDTO.class);
		
		return new ResponseEntity<>(dtoResponse, HttpStatus.OK);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Cliente pac = service.listarPorId(id);
		
		if(pac == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		service.eliminar(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
