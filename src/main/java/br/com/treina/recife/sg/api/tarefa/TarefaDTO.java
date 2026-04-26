package br.com.treina.recife.sg.api.tarefa;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.treina.recife.sg.api.projeto.Projeto;
import br.com.treina.recife.sg.api.tarefa.enums.PrioridadeTarefa;
import br.com.treina.recife.sg.api.tarefa.enums.TarefaStatus;
import br.com.treina.recife.sg.api.usuario.Usuario;

public record TarefaDTO(

        Long id,
        String titulo,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataCriacao,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataConclusao,
        PrioridadeTarefa prioridade,
        TarefaStatus status,
        Usuario usuario,
        Projeto projeto) {
}