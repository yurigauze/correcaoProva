package com.exemplo.helloworld.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "servico")
public class Servico {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome obrigatorio")
    private String nomeClente;
    
    @NotNull(message = "valor obrigatorio")
    private Double valorDoServico;

    private Double valorPago;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagamento;
    
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataTermino;

    @NotBlank(message = "Descrição obrigatoria")
    private String descricaoServico;
    private String status;

}
