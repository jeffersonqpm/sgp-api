package br.com.treina.recife.sg.api.usuario;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring Core: Indica que esta interface é um componente de acesso a dados
            // (Bean).
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
  // JpaRepository: Pertence ao Spring Data JPA.
  // Ele já traz métodos como save(), findAll(), findById() e deleteById()
  // prontos.
  // O <Usuario, Long> indica que a entidade é Usuario e a Chave Primária (ID) é
  // Long.

  // Query Methods (Métodos de Consulta Dinâmica):

  // O Spring Data lê o nome do método e gera o SQL:
  // "SELECT * FROM TB_USUARIO WHERE email = ? AND senha = ?"
  Optional<Usuario> findByEmailAndSenha(String email, String senha);

  // Gera o SQL: "SELECT * FROM TB_USUARIO WHERE cpf = ?"
  Optional<Usuario> findByCpf(String cpf);

  // Optional: É uma classe do Java 8+ que ajuda a evitar o erro
  // NullPointerException.
  // Ela diz: "Pode ser que eu encontre um usuário, ou não".
}

// =============================CODIGO ANTIGO==========================================================

// package br.com.treina.recife.sg.api.usuario;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

// // Optional<Usuario> findByEmail(String email);

// Optional<Usuario> findByEmailAndSenha(String email, String senha);

// Optional<Usuario> findByCpf(String cpf);

// }
