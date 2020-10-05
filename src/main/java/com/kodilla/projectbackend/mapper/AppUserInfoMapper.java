package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserInfoMapper {

    public AppUserInfo mapToAppUserInfo(AppUserInfoDto appUserInfoDto) {
        return new AppUserInfo(appUserInfoDto.getId(),appUserInfoDto.getFirstName(),
                appUserInfoDto.getEmail(),appUserInfoDto.getAppUser());
    }

    public AppUserInfoDto mapToAppUserInfoDto(AppUserInfo appUserInfo) {
        return new AppUserInfoDto(appUserInfo.getId(),appUserInfo.getFirstName(),
                appUserInfo.getEmail());
    }

    public List<AppUserInfoDto> mapToAppUserInfoDtoList(List<AppUserInfo> appUserInfoList) {
        return appUserInfoList.stream()
                .map(appUserInfo -> new AppUserInfoDto(appUserInfo.getId(),appUserInfo.getFirstName(),
                        appUserInfo.getEmail(),appUserInfo.getAppUser()))
                .collect(Collectors.toList());
    }
}
