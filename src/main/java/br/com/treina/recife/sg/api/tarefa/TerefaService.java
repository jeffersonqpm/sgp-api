package br.com.treina.recife.sg.api.tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    // SELECT * FROM TB_TAREFA
    public List<TarefaDTO> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();

        List<TarefaDTO> dtos = new ArrayList<>();

        for (Tarefa tarefa : tarefas) {

            dtos.add(tarefa.toDTO());

        }

        return dtos;
    }

    public TarefaDTO obterDadosDeTarefa(Long id) {

        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if (tarefa.isPresent()) {

            return tarefa.get().toDTO();

        }

        return null;
    }

    public Tarefa cadastrarTarefa(DadosTarefaDTO tarefa){

        return tarefaRepository.save(tarefa.toModel());
    }

}