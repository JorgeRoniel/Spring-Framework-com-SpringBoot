package dio.spring_restapi_security_jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import dio.spring_restapi_security_jwt.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    UserDetails findByLogin(String username);
    
}
