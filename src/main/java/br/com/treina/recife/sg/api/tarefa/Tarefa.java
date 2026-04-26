package br.com.treina.recife.sg.api.tarefa;

import java.time.LocalDate;

import br.com.treina.recife.sg.api.projeto.Projeto;
import br.com.treina.recife.sg.api.tarefa.enums.PrioridadeTarefa;
import br.com.treina.recife.sg.api.tarefa.enums.TarefaStatus;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_TAREFAS")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDate dataCriacao;

    private LocalDate dataConclusao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PrioridadeTarefa prioridade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TarefaStatus status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(nullable = false, name = "projeto_id")
    private Projeto projeto;

    public TarefaDTO toDTO() {

        return new TarefaDTO(

                id,
                titulo,
                descricao,
                dataCriacao,
                dataConclusao,
                prioridade,
                status,
                usuario,
                projeto);

    }

}
