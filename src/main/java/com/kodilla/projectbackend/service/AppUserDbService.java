package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserDbService implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Override
    public AppUser loadUserByUsername(String username)  {
        return appUserRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }

    public AppUser findUserById(Long userId){
        return appUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<AppUser> getAllAppUsers(){
        return appUserRepository.findAll();
    }

    public void saveAppUser(AppUser appUser){
        appUserRepository.save(appUser);
    }

    public Boolean checkExistsByUsername(String username){
        return appUserRepository.existsByUsername(username);
    }

    public void deleteAppUser(Long appUserId){
        appUserRepository.deleteById(appUserId);
    }

}
