package dio.spring_restapi_security_jwt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dio.spring_restapi_security_jwt.dtos.RegisterDTO;
import dio.spring_restapi_security_jwt.models.User;
import dio.spring_restapi_security_jwt.repository.UserRepository;

@Service
public class UserServices implements UserDetailsService{
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public Boolean createUser(RegisterDTO data){
        if(this.repository.findByLogin(data.login()) != null) return false;
        
        String enconderPass = encoder.encode(data.password());
        User user = new User(data.name(), data.login(), enconderPass, data.role());

        this.repository.save(user);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }
}
