package com.exemplo.helloworld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exemplo.helloworld.entity.Turma;

public interface TurmaRepository extends JpaRepository<Turma,Long>{
    
    @Query("from Turma where aluno.id=?1")
    public List<Turma> turmasPorAluno(Long idAluno);

    public List<Turma> findByAluno_IdAndCurso(Long id, String curso);
}