package kz.manap.flightstatusapi.services;

import kz.manap.flightstatusapi.models.Role;
import kz.manap.flightstatusapi.models.User;
import kz.manap.flightstatusapi.repositories.RoleRepository;
import kz.manap.flightstatusapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role defaultRole = roleRepository.findByCode("ROLE_USER");
        if (defaultRole == null) {
            throw new RuntimeException("Default role not found.");
        }

        user.setRole(defaultRole);

        userRepository.save(user);
    }
}
