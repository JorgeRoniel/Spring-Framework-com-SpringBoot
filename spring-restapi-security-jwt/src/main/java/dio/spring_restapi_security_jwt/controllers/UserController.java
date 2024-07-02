package dio.spring_restapi_security_jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.spring_restapi_security_jwt.dtos.AuthenticationDTO;
import dio.spring_restapi_security_jwt.dtos.LoginResponseDTO;
import dio.spring_restapi_security_jwt.dtos.RegisterDTO;
import dio.spring_restapi_security_jwt.models.User;
import dio.spring_restapi_security_jwt.security.TokenService;
import dio.spring_restapi_security_jwt.services.UserServices;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServices services;
    @Autowired
    private AuthenticationManager authenticantionManager;
    @Autowired
    private TokenService tokenService;

    @SuppressWarnings({ "rawtypes", "unused" })
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data){
        var usernamePass = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticantionManager.authenticate(usernamePass);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @SuppressWarnings("rawtypes")
    @PostMapping("/create")
    public ResponseEntity postUser(@RequestBody RegisterDTO data){
        if(services.createUser(data)) return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().build();
        
    }
}
