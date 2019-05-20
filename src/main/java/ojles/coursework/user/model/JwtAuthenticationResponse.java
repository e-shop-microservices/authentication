package ojles.coursework.user.model;

import lombok.Getter;
import lombok.Setter;
import ojles.coursework.user.entity.Role;

import java.util.Set;

@Getter
@Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private Set<Role> userRoles;

    public JwtAuthenticationResponse(String accessToken, Set<Role> userRoles) {
        this.accessToken = accessToken;
        this.userRoles = userRoles;
    }
}
