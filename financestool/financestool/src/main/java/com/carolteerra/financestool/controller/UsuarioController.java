package com.carolteerra.financestool.controller;

import com.carolteerra.financestool.DTO.TokenDTO;
import com.carolteerra.financestool.DTO.UsuarioDTO;
import com.carolteerra.financestool.exception.ErroAutenticacao;
import com.carolteerra.financestool.exception.RegraNegocioException;
import com.carolteerra.financestool.model.entity.Usuario;
import com.carolteerra.financestool.service.JwtService;
import com.carolteerra.financestool.service.LancamentoService;
import com.carolteerra.financestool.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final LancamentoService lancamentoService;
    private final JwtService jwtService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar( @RequestBody UsuarioDTO dto ) {
        try {
            Usuario usuarioAutenticado = service.autenticar(dto.getEmail(), dto.getSenha());
            String token = jwtService.gerarToken(usuarioAutenticado);
            TokenDTO tokenDTO = new TokenDTO( usuarioAutenticado.getNome(), token);
            return ResponseEntity.ok(tokenDTO);
        }catch (ErroAutenticacao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity salvar( @RequestBody UsuarioDTO dto ) {

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha()).build();

        try {
            Usuario usuarioSalvo = service.salvarUsuario(usuario);
            return new ResponseEntity(usuarioSalvo, HttpStatus.CREATED);
        }catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

/*    @GetMapping("{id}/saldo")
    public ResponseEntity obterSaldo( @PathVariable("id") Long id ) {
        Optional<Usuario> usuario = service.obterPorId(id);

        if(!usuario.isPresent()) {
            return new ResponseEntity( HttpStatus.NOT_FOUND );
        }

        BigDecimal saldo = lancamentoService.obterSaldoPorUsuario(id);
        return ResponseEntity.ok(saldo);
    }*/

}