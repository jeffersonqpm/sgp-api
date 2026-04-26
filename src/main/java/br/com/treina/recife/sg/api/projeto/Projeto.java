package br.com.treina.recife.sg.api.projeto;

import java.time.LocalDate;

import br.com.treina.recife.sg.api.projeto.enums.ProjetoStatus;
import br.com.treina.recife.sg.api.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_PROJETOS")
public class Projeto {

    @Id // chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataInicio;

    private LocalDate dataFinal;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjetoStatus status;

    @ManyToOne // relação com usuario
    // @OneToOne so pode ser um usuario para cada projeto
    @JoinColumn(nullable = false, name = "usuario_resp_id")
    private Usuario responsavel;

    /*
     * Apagar um projeto, todas as tarefas vinculadas a ele sejam apagadas
     * automaticamente
     */
    // @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Tarefa> tarefas;

    public ProjetoDTO toDTO() {

        return new ProjetoDTO(
                id,
                nome,
                descricao,
                dataInicio,
                dataFinal,
                status,
                responsavel);
    }

}
