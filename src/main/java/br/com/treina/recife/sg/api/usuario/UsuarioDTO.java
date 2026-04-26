package br.com.treina.recife.sg.api.usuario;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import br.com.treina.recife.sg.api.usuario.enums.UsuarioStatus;

/**
 * DTO de Saída: Utilizado para enviar os dados para quem consome a API.
 * Note que ele possui o campo 'idade', que não existe no banco,
 * mas é calculado na hora da conversão.
 */
public record UsuarioDTO(

                Long id,
                String nome,
                String cpf, // Aqui chegará o CPF já mascarado pelo método toDTO() da Entity
                String email,

                @JsonFormat(pattern = "dd/MM/yyyy")
                // Json: Define como esta data deve ser escrita no JSON de saída.
                // Sem isso, o JSON mostraria [2023, 10, 25] ou "2023-10-25".
                LocalDate dataNascimento,

                Integer idade, // Campo calculado, ótimo para o front-end não precisar fazer contas.

                UsuarioStatus status) {
}

// package br.com.treina.recife.sg.api.usuario;

// import java.time.LocalDate;

// import com.fasterxml.jackson.annotation.JsonFormat;

// import br.com.treina.recife.sg.api.usuario.enums.UsuarioStatus;

// public record UsuarioDTO(

// Long id,
// String nome,
// String cpf,
// String email,
// @JsonFormat(pattern = "dd/MM/yyyy")
// LocalDate dataNascimento,
// Integer idade,
// UsuarioStatus status) {

// }