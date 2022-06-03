package com.web.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.web.api.model.Matricula;
import com.web.api.repository.MatriculaRepository;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

	@Autowired
	private MatriculaRepository matriculaRepository;

	@GetMapping
	public List<Matricula> get() {
		return matriculaRepository.findAll();
	}

	@GetMapping("/{id}")
    public Matricula getById(@PathVariable Long id) {
		System.out.println(id);
		var matriculaOptional = matriculaRepository.findById(id);

        if (matriculaOptional.isEmpty()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return matriculaOptional.get();
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Matricula insert(@RequestBody Matricula matricula) {
		return matriculaRepository.save(matricula);
	}

	@DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        var matriculaOptional = matriculaRepository.findById(id);

        if (matriculaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        matriculaRepository.delete(matriculaOptional.get());
    }

	@PutMapping("/{id}")
	public Matricula replaceMatriculaById(@PathVariable Long id, @RequestBody Matricula newMatricula) {
		var matriculaOptional = matriculaRepository.findById(id);

		if (matriculaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

		newMatricula.setId(id);

		return matriculaRepository.save(newMatricula);
	}

}
