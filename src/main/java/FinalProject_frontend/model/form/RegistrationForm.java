package FinalProject_frontend.model.form;

import org.springframework.security.crypto.password.PasswordEncoder;
import FinalProject_frontend.model.UserDetail;
import lombok.Data;
@Data
    public class RegistrationForm {
    private String username;
    private String password;
    public UserDetail toUser(PasswordEncoder passwordEncoder) {
        return UserDetail.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
    }
}
