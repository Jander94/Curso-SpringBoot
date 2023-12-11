package curso.springboot.Vendas.config;

import curso.springboot.Vendas.domain.entity.Usuario;
import curso.springboot.Vendas.domain.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByLogin(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("User not found: " + username));

    return new User(
            usuario.getUsername(),
            usuario.getPassword(),
            true,
            true,
            true,
            true,
            usuario.getAuthorities()
    );
  };
}
