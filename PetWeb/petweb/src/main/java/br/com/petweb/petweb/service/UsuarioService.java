package br.com.petweb.petweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Usuario;
import br.com.petweb.petweb.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {

        String senha = usuario.getSenhaUsuario();
        String confirmarSenha = usuario.getConfirmarSenha();
    
        // Verifica confirmação
        if (!senha.equals(confirmarSenha)) {
    
            throw new RuntimeException(
                "As senhas não coincidem."
            );
        }
    
        // Regex melhorada
        String regex =
            "^(?=.*[a-z])" +      // minúscula
            "(?=.*[A-Z])" +       // maiúscula
            "(?=.*\\d)" +         // número
            "(?=.*[^A-Za-z0-9])" + // especial
            ".{8,}$";             // mínimo 8 caracteres
    
        if (!senha.matches(regex)) {
    
            throw new RuntimeException(
                "A senha deve conter no mínimo 8 caracteres, letra maiúscula, minúscula, número e caractere especial."
            );
        }
    
        usuario.setSenhaUsuario(
            passwordEncoder.encode(senha)
        );
    
        return usuarioRepository.save(usuario);
    }

}
