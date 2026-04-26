
package br.com.treina.recife.sg.api.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Spring Core: Define que esta classe é um "Serviço" gerenciado pelo Spring.
         // Aqui é onde as transações de banco devem ocorrer.
public class UsuarioService {

    @Autowired // Spring Core: Injeta o Repository para que o Service possa acessar o banco de
               // dados.
    private UsuarioRepository usuarioRepository;

    /**
     * Lista todos os usuários e converte para DTO.
     * Dica: Você poderia usar Stream API para encurtar isso:
     * return usuarioRepository.findAll().stream().map(Usuario::toDTO).toList();
     */
    // SQL: SELECT * FROM TB_USUARIO;
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> dtos = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            dtos.add(usuario.toDTO());
        }
        return dtos;
    }

    /**
     * SQL: SELECT * FROM TB_USUARIO WHERE id = ?;
     */
    public UsuarioDTO obterDadosDoUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id); // O findById já é padrão do JpaRepository.

        if (usuario.isPresent()) {
            return usuario.get().toDTO();
        }
        return null; // Dica: No futuro, lançar uma Exception personalizada aqui é melhor que
                     // retornar null.
    }

    /**
     * SQL: INSERT INTO TB_USUARIO (nome, cpf, email, senha, data_nascimento,
     * status)
     * VALUES ('Nome', '123...', 'email@...', 'senha123', '1990-01-01', 'ATIVO');
     */
    public Usuario cadastrarUsuario(DadosUsuarioDTO usuario) {
        // Aqui você poderia colocar uma regra: "if
        // (usuarioRepository.existsByCpf(...))"
        return usuarioRepository.save(usuario.toModel()); // save(): Método do JPA que executa o INSERT.
    }

    /**
     * O Hibernate faz dois passos aqui:
     * 1. SQL: SELECT * FROM TB_USUARIO WHERE id = ?; (Para verificar se existe)
     * 2. SQL: UPDATE TB_USUARIO SET nome=?, cpf=?, email=?, senha=?,
     * data_nascimento=?, status=?
     * WHERE id = ?;
     */
    public Usuario atualizarUsuario(Long id, DadosUsuarioDTO dados) {
        Usuario usuario = dados.toModel();
        usuario.setId(id); // Garante que o JPA fará um UPDATE em vez de um novo INSERT.
        return usuarioRepository.save(usuario);
    }

    /**
     * SQL: DELETE FROM TB_USUARIO WHERE id = ?;
     */
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id); // deleteById(): Método do JPA que executa o DELETE.
    }

    /**
     * SQL: SELECT * FROM TB_USUARIO WHERE cpf = ?;
     */
    public UsuarioDTO buscarUsuarioPeloCpf(String cpf) {
        Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf); // Usa o método customizado que você criou no
                                                                      // Repository.

        if (usuario.isPresent()) {
            return usuario.get().toDTO();
        }
        return null;
    }

    /**
     * SQL: SELECT * FROM TB_USUARIO WHERE email = ? AND senha = ?;
     */
    public UsuarioDTO buscarUsuarioPeloEmailSenha(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuario.isPresent()) {
            return usuario.get().toDTO();
        }
        return null;
    }
}

// ==================================CODIGO ANTIGO ============================

// package br.com.treina.recife.sg.api.usuario;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class UsuarioService {

// @Autowired
// private UsuarioRepository usuarioRepository;

// public List<UsuarioDTO> listarUsuarios() {
// List<Usuario> usuarios = usuarioRepository.findAll();

// List<UsuarioDTO> dtos = new ArrayList<>();

// for (Usuario usuario : usuarios) {

// dtos.add(usuario.toDTO());

// }

// return dtos;
// }

// // SELECT * FROM TB_USUARIO
// public UsuarioDTO obterDadosDoUsuario(Long id) {

// Optional<Usuario> usuario = usuarioRepository.findById(id);

// if (usuario.isPresent()) {

// return usuario.get().toDTO();

// }

// return null;
// }

// // INSERT INTO TB_USUARIOS VALUES ...
// public Usuario cadastrarUsuario(DadosUsuarioDTO usuario) {

// return usuarioRepository.save(usuario.toModel());
// }

// // UPDATE TB_USUARIOS .. WHERE ID = ?
// public Usuario atualizarUsuario(Long id, DadosUsuarioDTO dados) {

// Usuario usuario = dados.toModel();
// usuario.setId(id);
// return usuarioRepository.save(usuario);
// }

// // DELETE FROM TB_USUARIOS WHERE ID = ?
// public void excluirUsuario(Long id) {

// usuarioRepository.deleteById(id);
// }

// // SELECT * FROM TB_USUARIOS WHERE CPF = ?
// public UsuarioDTO buscarUsuarioPeloCpf(String cpf) {
// Optional<Usuario> usuario = usuarioRepository.findByCpf(cpf);

// if (usuario.isPresent()) {
// return usuario.get().toDTO();

// }
// return null;
// }

// public UsuarioDTO buscarUsuarioPeloEmailSenha(String email, String senha) {

// Optional<Usuario> usuario = usuarioRepository.findByEmailAndSenha(email,
// senha);

// if (usuario.isPresent()) {
// return usuario.get().toDTO();

// }

// return null;
// }

// }
