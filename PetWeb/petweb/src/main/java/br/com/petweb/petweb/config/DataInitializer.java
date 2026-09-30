package br.com.petweb.petweb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.petweb.petweb.entity.Usuario;
import br.com.petweb.petweb.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            System.out.println("======================================");
            System.out.println("DATA INITIALIZER EXECUTANDO...");
            System.out.println("======================================");

            if (usuarioRepository.findByLoginUsuario("admin").isEmpty()) {

                Usuario admin = new Usuario();

                admin.setNomeUsuario("Administrador");
                admin.setEmailUsuario("admin@petweb.com");
                admin.setTelefoneUsuario("(00) 00000-0000");
                admin.setCpfUsuario("000.000.000-00");
                admin.setLoginUsuario("admin");

                admin.setSenhaUsuario(
                    passwordEncoder.encode("Admin@123")
                );

                admin.setRole("ROLE_ADMIN");

                usuarioRepository.save(admin);

                System.out.println("======================================");
                System.out.println("ADMINISTRADOR CRIADO!");
                System.out.println("Login: admin");
                System.out.println("Senha: Admin@123");
                System.out.println("Role: ROLE_ADMIN");
                System.out.println("======================================");

            } else {

                System.out.println("======================================");
                System.out.println("ADMINISTRADOR JÁ EXISTE.");
                System.out.println("======================================");
            }
        };
    }
}