package FinalProject_frontend.model.form;

import org.springframework.security.crypto.password.PasswordEncoder;
import FinalProject_frontend.model.AppUser;
import lombok.Data;
@Data
    public class RegistrationForm {
    private String username;
    private String password;
    public AppUser toUser(PasswordEncoder passwordEncoder) {
        return AppUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
