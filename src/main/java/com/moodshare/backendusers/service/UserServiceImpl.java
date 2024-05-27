package com.moodshare.backendusers.service;

import com.moodshare.backendusers.models.Rol;
import com.moodshare.backendusers.models.User;
import com.moodshare.backendusers.repositories.RolRepository;
import com.moodshare.backendusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolRepository rolRepository;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Buscar el rol "USER" existente
        Rol userRole = rolRepository.findByName("USER");

        // Asignar el rol "USER" al nuevo usuario
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id_usuario) {
        Optional<User> optionalUser = userRepository.findById(id_usuario);
        return optionalUser.orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(Long id_usuario, User updatedUser) {
        User user = getUserById(id_usuario);
        if (user != null) {
            user.setName(updatedUser.getName());
            user.setApellido(updatedUser.getApellido());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long id_usuario) {
        userRepository.deleteById(id_usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}

// TODO: FUNCIONA OKA SIN ID
//package com.moodshare.backendusers.service;
//
//import com.moodshare.backendusers.models.Rol;
//import com.moodshare.backendusers.models.User;
//import com.moodshare.backendusers.repositories.RolRepository;
//import com.moodshare.backendusers.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements IUserService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RolRepository rolRepository;
//
//
//    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public User save(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        // Buscar el rol "USER" existente
//        Rol userRole = rolRepository.findByName("USER");
//        if (userRole == null) {
//            throw new RuntimeException("Role USER not found");
//        }
//
//        // Asignar el rol "USER" al nuevo usuario
//        user.setRoles(Collections.singletonList(userRole));
//        return userRepository.save(user);
//    }
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public User getUserById(Long id) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        return optionalUser.orElse(null);
//    }
//
//    @Override
//    public User getUserByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public User updateUser(Long id, User updatedUser) {
//        User user = getUserById(id);
//        if (user != null) {
//            user.setName(updatedUser.getName());
//            user.setApellido(updatedUser.getApellido());
//            user.setEmail(updatedUser.getEmail());
//            return userRepository.save(user);
//        }
//        return null;
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException("Usuario no encontrado con el email: " + email);
//        }
//
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                authorities
//        );
//    }
//}
