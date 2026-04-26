package br.com.treina.recife.sg.api.tarefa;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> cadastrar(@Valid @RequestBody DadosTarefaDTO tarefa) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tarefaService.cadastrarTarefa(tarefa).toDTO());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DadosTarefaDTO dadosTarefa) {
        TarefaDTO tarefa = tarefaService.obterDadosDeTarefa(id);

        if (Objects.isNull(tarefa)) {

            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(tarefaService.atuakizarTarefa(id, dadosTarefa).toDTO()); // 200
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> listar() {
        return ResponseEntity.ok(tarefaService.listarTarefas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> obterDadosPorId(@PathVariable Long id) {

        TarefaDTO tarefa = tarefaService.obterDadosDeTarefa(id);

        if (Objects.isNull(tarefa)) {
            return ResponseEntity.notFound().build();

        }

        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        TarefaDTO tarefa = tarefaService.obterDadosDeTarefa(id);

        if (Objects.isNull(tarefa)) {
            return ResponseEntity.notFound().build();

        }

        tarefaService.excluirTarefa(id);

        return ResponseEntity.noContent().build();// 204
    }
}
