package br.com.treina.recife.sg.api.tarefa;

import java.time.LocalDate;

import br.com.treina.recife.sg.api.projeto.Projeto;
import br.com.treina.recife.sg.api.tarefa.enums.PrioridadeTarefa;
import br.com.treina.recife.sg.api.tarefa.enums.TarefaStatus;
import br.com.treina.recife.sg.api.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTarefaDTO(

        @NotBlank String titulo,

        String descrição,

        @NotNull LocalDate dataCriacao,

        @NotNull LocalDate dataConclusao,

        @NotNull PrioridadeTarefa prioraidade,

        @NotNull TarefaStatus status,

        Long usuarioId,

        @NotNull Long projetoId

) {

    public Tarefa toModel() {

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(titulo);
        tarefa.setDescricao(descrição);
        tarefa.setDataCriacao(dataCriacao);
        tarefa.setDataConclusao(dataConclusao);
        tarefa.setPrioridade(prioraidade);
        tarefa.setStatus(status);

        Usuario usuario = new Usuario();

        usuario.setId(usuarioId);
        tarefa.setUsuario(usuario);

        Projeto projeto = new Projeto();

        projeto.setId(projetoId);
        tarefa.setProjeto(projeto);

        return tarefa;

    }
}