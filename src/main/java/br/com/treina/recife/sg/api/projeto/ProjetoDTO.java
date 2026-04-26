package br.com.treina.recife.sg.api.projeto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.treina.recife.sg.api.projeto.enums.ProjetoStatus;
import br.com.treina.recife.sg.api.usuario.Usuario;

public record ProjetoDTO(

        Long id,
        String nome,
        String descricao,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
        @JsonFormat(pattern = "dd/MM/yyyy") LocalDate dataFinal,
        ProjetoStatus status,
        Usuario responsavel) {

}
