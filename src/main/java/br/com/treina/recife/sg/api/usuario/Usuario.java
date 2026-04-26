package br.com.treina.recife.sg.api.usuario;

import java.time.LocalDate;
import java.time.Period;

import br.com.treina.recife.sg.api.usuario.enums.UsuarioStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Data                       // Lombok: Gera Getters, Setters, toString, equals e hashCode automaticamente.
@AllArgsConstructor        // Lombok: Gera um construtor com todos os atributos da classe.
@NoArgsConstructor         // Lombok: Gera um construtor vazio (obrigatório para o Hibernate/JPA).
@Entity(name = "TB_USUARIO") // Jakarta Persistence: Define que esta classe é uma tabela no banco de dados.

public class Usuario {

    @Id // Jakarta Persistence: Define que este atributo é a Chave Primária (PK) da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Jakarta Persistence: Define a estratégia de auto-incremento do banco.
    private Long id;

    @Column(nullable = false, length = 100) // Jakarta Persistence: Configura a coluna (não nula e tamanho máx de 100).
    private String nome;

    @Column(nullable = false, length = 11) // Jakarta Persistence: Configura a coluna (não nula e tamanho para CPF).
    private String cpf;

    @Column(nullable = false) // Jakarta Persistence: Define que o campo de email não pode ser nulo no banco.
    private String email;

    @Column(nullable = false, length = 19) // Jakarta Persistence: Define obrigatoriedade e tamanho para a senha.
    private String senha;

    @Column(nullable = false) // Jakarta Persistence: Mapeia o campo de data para uma coluna do tipo DATE no banco.
    private LocalDate dataNascimento;

    @Column(nullable = false) // Jakarta Persistence: Define que o campo de status não pode ser nulo.
    @Enumerated(EnumType.STRING) // Jakarta Persistence: Salva o nome do Enum no banco (ex: "ATIVO") em vez do número (0, 1).
    private UsuarioStatus status;

    // Método de conversão para DTO (Data Transfer Object)
    public UsuarioDTO toDTO() {
        // Calcula a idade baseada na data de nascimento
        Period periodo = Period.between(dataNascimento, LocalDate.now());

        // Máscara simples de CPF para segurança na exibição
        String cpfFormatado = cpf.substring(0, 3) + ".***.**-**";

        return new UsuarioDTO(
                id,
                nome,
                cpfFormatado,
                email,
                dataNascimento,
                periodo.getYears(),
                status);
    }
}