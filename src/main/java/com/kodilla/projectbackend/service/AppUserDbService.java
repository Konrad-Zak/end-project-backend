package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUser;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findByUsername(username);
    }

    public List<AppUser> getAllAppUsers(){
        return appUserRepository.findAll();
    }

    public Boolean saveAppUser(AppUser appUser){
        appUserRepository.save(appUser);
        return appUserRepository.existsById(appUser.getId());
    }

    public void deleteAppUser(Long appUserId){
        appUserRepository.deleteById(appUserId);
    }

}
