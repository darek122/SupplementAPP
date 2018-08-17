package pl.dariuszskrzypczak.SupplementAPP.models.services;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.dariuszskrzypczak.SupplementAPP.models.UserEntity;

@Service
@Data
@Scope(scopeName = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionService {
    private boolean isLogin;
    private UserEntity userEntity;
}
