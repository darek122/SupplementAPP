package pl.dariuszskrzypczak.SupplementAPP.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dariuszskrzypczak.SupplementAPP.models.AdminEntity;
import pl.dariuszskrzypczak.SupplementAPP.models.UserEntity;
import pl.dariuszskrzypczak.SupplementAPP.models.forms.RegisterForm;
import pl.dariuszskrzypczak.SupplementAPP.models.repositories.AdminRepository;
import pl.dariuszskrzypczak.SupplementAPP.models.repositories.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    final UserRepository userRepository;
    final SessionService sessionService;
    final AdminRepository adminRepository;
    @Autowired
    public AuthService(UserRepository userRepository, SessionService sessionService, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.sessionService = sessionService;
        this.adminRepository = adminRepository;

    }

    public boolean tryToRegister(RegisterForm registerForm){
        if(userRepository.existsByEmail(registerForm.getEmail())){
            return false;
        }
        UserEntity userEntity=createUserEntityFromForm(registerForm);
        userRepository.save(userEntity);
        return true;

    }

        private  UserEntity createUserEntityFromForm(RegisterForm registerForm){
        UserEntity userEntity= new UserEntity();
        userEntity.setUsername(registerForm.getUsername());
        userEntity.setPassword(registerForm.getPassword());
        userEntity.setEmail(registerForm.getEmail());
        return userEntity;
        }

        public boolean tryToLogin(String email, String password){
            Optional<UserEntity> userEntity=
                    userRepository.findByEmailAndPassword(email,password);
            if(userEntity.isPresent()){
                sessionService.setLogin(true);
                sessionService.setUserEntity(userEntity.get());
            }
            return userEntity.isPresent();

        }
        public boolean adminLogin(String email, String password){
            Optional<AdminEntity> adminEntity=
                    adminRepository.findByEmailAndPassword(email,password);
            if(adminEntity.isPresent()){
                sessionService.setLogin(true);
                sessionService.setAdminEntity(adminEntity.get());
            }
            return adminEntity.isPresent();
        }




}
