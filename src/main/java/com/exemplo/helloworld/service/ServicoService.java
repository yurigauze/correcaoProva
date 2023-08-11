package com.exemplo.helloworld.service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exemplo.helloworld.entity.Servico;
import com.exemplo.helloworld.entity.Variancia;
import com.exemplo.helloworld.repository.ServicoRepository;

import lombok.experimental.var;

@Service
public class ServicoService {
    
    @Autowired
    private ServicoRepository servicoRepository;


    public List<Servico> buscarStatus(String status){
        return servicoRepository.findByStatusEquals(status);
    }

    public List<Servico> listarAbertos(){
        return servicoRepository.findAberto();
    }


    public List<Servico> listarTodos(){
        return servicoRepository.findAll();
    }

    public Servico salvar(Servico servico){

        atualizar(servico);
        verificarPagamento(servico);
        return servicoRepository.saveAndFlush(servico);
    }

    public Servico atualizar(Servico servico) {
        atualizarStatus(servico);
        verificarPagamento(servico);
        return servicoRepository.saveAndFlush(servico);
    }

     public void excluir(Long id) {
                  
        Servico servico = servicoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Servico não encontrado."));
        servico.setStatus("excluido");
       atualizar(servico);


    }

    private void atualizarStatus(Servico servico){
        if(servico.getDataTermino() == null){
            servico.setStatus("Pendente");
        } else {
            servico.setStatus("Realizado");
        }
    }

    public void excluir(Date dataInicial, Date dataFinal){
        List<Servico> servico=servicoRepository.servidoDataInicio(dataInicial, dataFinal);
        for(Servico s:servico){
            s.setStatus("excluido");
            atualizar(s);
        }
    }

    private void verificarPagamento(Servico servico){
        if(servico.getValorPago() == null && servico.getDataPagamento() != null || servico.getDataPagamento() == null && servico.getValorPago() != null){
            throw new IllegalArgumentException("Informar a data de pagamento e valor pago");
        }
    }

    public ResponseEntity<Variancia> calculoVariancia(){
        List<Servico> servicos = servicoRepository.servicosPagos(); 
        Double media = calculoMedia(servicos);
        Double variancia  = calculoVariancia(servicos, media);



        return new ResponseEntity<>(new Variancia(media, variancia), HttpStatus.OK); 

    }

    private Double calculoVariancia(List<Servico> servico, Double media){
        Double variancia = 0.;
        for (Servico s:servico){
            variancia += Math.pow(s.getValorPago()-media,2 );
        }

        variancia = variancia/servico.size();
        return variancia;
    }

    private Double calculoMedia(List<Servico> servicos){
        Double media = 0.;
        for(Servico s:servicos){
            media += s.getValorPago();
        }
        media = media/servicos.size();
        return media;
    }
}
