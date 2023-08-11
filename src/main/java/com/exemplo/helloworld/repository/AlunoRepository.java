package com.exemplo.helloworld.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exemplo.helloworld.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno,Long>{
    
    @Query("from Aluno where cidade like %?1% order by nome desc")
    public List<Aluno> alunosCidade(String cidade);

    @Query("from Aluno where cidade like %?2% and nome like %?1%")
    public List<Aluno> alunosCidadeNome(String nome, String cidade);

    List<Aluno> findByCpfIsNull();

    public List<Aluno> findFirst2ByNome(String nome);

    public Optional<Aluno> getByEmail(String email);


    public Integer countByNome(String nome);

    List<Aluno> findByNomeIsNot(String nome);
    List<Aluno> findByNomeEquals(String nome);

    List<Aluno> findByNomeIsNull();
    List<Aluno> findByNomeIsNotNull();

    List<Aluno> findByStatusTrue();
    List<Aluno> findByStatusFalse();

    List<Aluno> findByNomeContaining(String nome);



    List<Aluno> findByRaBetween(Integer raInicial, Integer raFinal);


    List<Aluno> findByDataNascimentoAfter(Date dataNascimento);
    List<Aluno> findByDataNascimentoBefore(Date dataNascimento);

    List<Aluno> findByNomeOrRa(String nome, Integer ra);
    List<Aluno> findByNomeOrRaAndStatus(String nome, Integer ra, Boolean status);

    List<Aluno> findByNomeOrderByNome(String nome);
    List<Aluno> findByNomeOrderByNomeAsc(String nome);
    List<Aluno> findByNomeOrderByNomeDesc(String nome);



 
    
}
