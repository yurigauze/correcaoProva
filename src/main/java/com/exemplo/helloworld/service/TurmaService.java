package com.exemplo.helloworld.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.helloworld.entity.Turma;
import com.exemplo.helloworld.repository.TurmaRepository;

@Service
public class TurmaService {
  
    @Autowired
    private TurmaRepository turmaRepository;

    public List<Turma> turmasPorAluno(Long idAluno){
        return turmaRepository.turmasPorAluno(idAluno);
    }

 
  

}
