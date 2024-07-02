package dio.spring_restapi_security_jwt.dtos;

import dio.spring_restapi_security_jwt.models.UserRoles;

public record RegisterDTO(String name, String login, String password, UserRoles role) {
    
}
