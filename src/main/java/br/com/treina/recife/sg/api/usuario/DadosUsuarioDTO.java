package br.com.treina.recife.sg.api.usuario;

import java.time.LocalDate;

import br.com.treina.recife.sg.api.usuario.enums.UsuarioStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Um Record no Java é uma classe imutável que já possui getters, 
 * constructor, equals, hashCode e toString prontos.
 */
public record DadosUsuarioDTO(

        @NotBlank // Bean Validation: Impede que o campo seja nulo ou vazio ("").
        @Size(min = 3, max = 50) // Bean Validation: Define limites de caracteres.
        String nome,

        @NotBlank // Bean Validation: Pertence à especificação Jakarta Validation.
        @Size(max = 11) // Bean Validation: Garante que o CPF não ultrapasse o tamanho do banco.
        String cpf,

        @NotBlank 
        @Email // Bean Validation: Valida se o formato do texto é de um e-mail real (ex@ex.com).
        String email,

        @NotBlank 
        @Size(max = 19) 
        String senha,

        @NotNull // Bean Validation: Usado para objetos (como LocalDate), garante que não seja null.
        LocalDate dataNascimento,

        @NotNull // Bean Validation: Garante que o status do enum seja enviado na requisição.
        UsuarioStatus status

) {
    /**
     * Método auxiliar para converter o DTO (dados que chegam da API) 
     * para a Model (objeto que o Hibernate salva no banco).
     */
    public Usuario toModel() {
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setDataNascimento(dataNascimento);
        usuario.setStatus(status);

        return usuario;
    }
}








// package br.com.treina.recife.sg.api.usuario;

// import java.time.LocalDate;

// import br.com.treina.recife.sg.api.usuario.enums.UsuarioStatus;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.Size;

// public record DadosUsuarioDTO(

//         @NotBlank @Size(min = 3, max = 50) String nome,

//         @NotBlank @Size(max = 11) String cpf,

//         @NotBlank @Email String email,

//         @NotBlank @Size(max = 19) String senha,

//         @NotNull LocalDate dataNascimento,

//         @NotNull UsuarioStatus status

// ) {

//     public Usuario toModel() {

//         Usuario usuario = new Usuario();

//         usuario.setNome(nome);
//         usuario.setCpf(cpf);
//         usuario.setEmail(email);
//         usuario.setSenha(senha);
//         usuario.setDataNascimento(dataNascimento);
//         usuario.setStatus(status);

//         return usuario;
//     }
// }
