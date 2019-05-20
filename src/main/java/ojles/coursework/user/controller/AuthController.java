package ojles.coursework.user.controller;

import lombok.RequiredArgsConstructor;
import ojles.coursework.user.entity.User;
import ojles.coursework.user.model.JwtAuthenticationResponse;
import ojles.coursework.user.model.LoginRequest;
import ojles.coursework.user.security.JwtTokenProvider;
import ojles.coursework.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        String usernameOrEmail = loginRequest.getUsernameOrEmail();
        User user = userService.findByUsernameOrEmail(usernameOrEmail);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, user.getRoles()));
    }
}
