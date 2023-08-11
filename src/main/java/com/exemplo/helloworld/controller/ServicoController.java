package com.exemplo.helloworld.controller;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.helloworld.entity.Servico;
import com.exemplo.helloworld.entity.Variancia;
import com.exemplo.helloworld.service.ServicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servicos")
@CrossOrigin
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // localhost:8080/servicos/servico-status?status=ativo
    @GetMapping("/servico-status")
    public List<Servico> listarStatus(@RequestParam(value = "status", defaultValue = "pendente") String status) {
        return servicoService.buscarStatus(status);
    }


    @GetMapping("/servico-abertos")
    public List<Servico> listarAbertos() {
        return servicoService.listarAbertos();
    }


    // localhost:8080/servicos - com o verbo get
    @GetMapping
    public List<Servico> listarTodos() {
        return servicoService.listarTodos();
    }

    @GetMapping("/calculo-variancia")
    public ResponseEntity<Variancia> calculoVariancia(){
        return servicoService.calculoVariancia();
    }

    // localhost:8080/servicos - com verbo post
    @PostMapping
    public Servico salvar(@RequestBody @Valid Servico servico) {
        return servicoService.salvar(servico);
    }

    @PutMapping
    public Servico alterar(@RequestBody @Valid Servico servico) {
        return servicoService.atualizar(servico);

    }

    @DeleteMapping("/{dataInicio}/{dataTermino}")
    public void excluir(@PathVariable("dataInicio") @DateTimeFormat(pattern = "yyyy-mm-dd") Date dataInicio, @PathVariable("dataFinal") @DateTimeFormat(pattern = "yyyy-mm-dd") Date dataFinal){
        servicoService.excluir(dataInicio, dataFinal);
    }

    // localhost:8080/servicos/1 - com o verbo delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        try {
            servicoService.excluir(id);
            return ResponseEntity.ok("Produto excluído com sucesso");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
