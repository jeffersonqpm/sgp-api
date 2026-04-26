package br.com.treina.recife.sg.api.tarefa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

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

    public Tarefa cadastrarTarefa(DadosTarefaDTO tarefa) {

        return tarefaRepository.save(tarefa.toModel());
    }

    public Tarefa atuakizarTarefa(Long id, DadosTarefaDTO dados) {

        Tarefa tarefa = dados.toModel();
        tarefa.setId(id);
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

}