package com.exemplo.helloworld.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exemplo.helloworld.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico,Long>{
    
    List<Servico> findByStatusEquals(String nomeStatus);
    
    @Query("FROM Servico WHERE status <> 'excluido' and valorDoServico is null and dataPagamento is null")
    List<Servico> findAberto();

    @Query("from Servico where dataInicio between ?1 and ?2 and status <> 'excluido'")
    List<Servico> servidoDataInicio(Date dateInicio, Date dataFinal);

    @Query("from Servico where valorPago <> null and dataPagamento <> null")
    List<Servico> servicosPagos();
    

}
