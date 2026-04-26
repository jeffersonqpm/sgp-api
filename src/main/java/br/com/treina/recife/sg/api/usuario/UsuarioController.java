package br.com.treina.recife.sg.api.usuario;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController // Spring Web: Combinação de @Controller + @ResponseBody. Indica que o retorno dos métodos será o corpo da resposta (JSON).
@RequestMapping("/api/usuarios") // Spring Web: Define a URL base para todos os endpoints deste controller.
public class UsuarioController {

    @Autowired // Spring Core: Faz a Injeção de Dependência. O Spring "instancia" o service e o coloca aqui para você usar.
    private UsuarioService usuarioService;

    @PostMapping // Spring Web: Mapeia requisições do tipo POST. Usado para criação de recursos.
    public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody DadosUsuarioDTO usuario) {
        // @Valid: Ativa as validações que você colocou no Record (DadosUsuarioDTO).
        // @RequestBody: Indica que os dados do usuário virão no corpo (Body) da requisição JSON.

        Usuario usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);
        UsuarioDTO usuarioDTO = usuarioCadastrado.toDTO();
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO); // Retorna HTTP 201 (Created).
    }

    @GetMapping // Spring Web: Mapeia requisições do tipo GET para listar ou buscar dados.
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios()); // Retorna HTTP 200 (OK).
    }

    @GetMapping("/{id}") // Spring Web: Mapeia um GET com uma variável de caminho (id).
    public ResponseEntity<UsuarioDTO> obterDadosPeloId(@PathVariable Long id) {
        // @PathVariable: Vincula o {id} da URL ao parâmetro Long id do método.

        UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);
        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404 (Not Found).
        }
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}") // Spring Web: Mapeia o verbo HTTP DELETE para remover recursos.
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        UsuarioDTO usario = usuarioService.obterDadosDoUsuario(id);
        if (Objects.isNull(usario)) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build(); // Retorna HTTP 204 (No Content) - sucesso sem corpo de resposta.
    }

    @PutMapping("/{id}") // Spring Web: Mapeia o verbo HTTP PUT para atualização total de um recurso.
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DadosUsuarioDTO dados) {
        UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);
        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dados).toDTO());
    }

    @GetMapping("/buscaPorCpf") // Spring Web: Mapeia uma busca que usa parâmetros de consulta (?cpf=...).
    public ResponseEntity<UsuarioDTO> consultarPeloCpf(@RequestParam String cpf) {
        // @RequestParam: Pega o valor enviado na URL após a interrogação (ex: .../buscaPorCpf?cpf=123).

        UsuarioDTO usuario = usuarioService.buscarUsuarioPeloCpf(cpf);
        if (Objects.isNull(usuario)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
}


// package br.com.treina.recife.sg.api.usuario;

// import java.util.List;
// import java.util.Objects;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import jakarta.validation.Valid;

// @Controller
// @RequestMapping("/api/usuarios")
// public class UsuarioController {

//     @Autowired

//     private UsuarioService usuarioService;

//     @PostMapping
//     public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody DadosUsuarioDTO usuario) {

//         Usuario usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);

//         UsuarioDTO usuarioDTO = usuarioCadastrado.toDTO();
//         return ResponseEntity.status(HttpStatus.CREATED) // retorna 2001
//                 .body(usuarioDTO);
//         // .body(usuarioService.cadastrarUsuario(usuario).toDTO());

//     }

//     @GetMapping
//     // public ResponseEntity<List<Usuario>> listar() {
//     public ResponseEntity<List<UsuarioDTO>> listar() {
//         return ResponseEntity.ok(usuarioService.listarUsuarios());// retorna 200
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<UsuarioDTO> obterDadosPeloId(@PathVariable Long id) {

//         UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);

//         if (Objects.isNull(usuario)) {
//             return ResponseEntity.notFound().build(); // HTTP 404

//         }

//         return ResponseEntity.ok(usuario); // HTTP 200

//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> excluir(@PathVariable Long id) {
//         UsuarioDTO usario = usuarioService.obterDadosDoUsuario(id);

//         if (Objects.isNull(usario)) {
//             return ResponseEntity.notFound().build();

//         }

//         usuarioService.excluirUsuario(id);
//         return ResponseEntity.noContent().build();// noContent(): retorna HTTP 204, body vazio
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DadosUsuarioDTO dados) {

//         UsuarioDTO usuario = usuarioService.obterDadosDoUsuario(id);

//         if (Objects.isNull(usuario)) {
//             return ResponseEntity.notFound().build();

//         }

//         return ResponseEntity.ok(usuarioService.atualizarUsuario(id, dados).toDTO());
//     }

//     @GetMapping("/buscaPorCpf")

//     public ResponseEntity<UsuarioDTO> consultarPeloCpf(@RequestParam String cpf) {
//         UsuarioDTO usuario = usuarioService.buscarUsuarioPeloCpf(cpf);

//         if (Objects.isNull(usuario)) {

//             return ResponseEntity.notFound().build();

//         }

//         return ResponseEntity.ok(usuario);
//     }

//     // @GetMapping("/buscaPorCredencias")
//     // public ResponseEntity<UsuarioDTO> consultarPelasCredencias(@RequestBody CredenciaisDTO credencias){

//     // }

// }
