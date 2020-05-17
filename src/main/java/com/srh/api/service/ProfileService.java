package com.srh.api.service;

import com.srh.api.model.Profile;
import com.srh.api.repository.ProfileRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Profile findByName(String name) {
        Optional<Profile> profile = profileRepository.findByName(name);

        if (profile.isPresent())
            return profile.get();

        throw new ObjectNotFoundException(name, Profile.class.getName());
    }

    public List<Profile> generateProfileList(boolean isAdmin) {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(findByName("ROLE_USER"));

        if (isAdmin) {
            profiles.add(findByName("ROLE_ADMIN"));
        }

        return profiles;
    }
}
