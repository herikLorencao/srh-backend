package com.srh.api.config;

import com.srh.api.builder.ApiUserBuilder;
import com.srh.api.builder.ProfileBuilder;
import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;
import com.srh.api.repository.ApiUserRepository;
import com.srh.api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbSeeder {
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private Profile adminProfile;
    private Profile userProfile;

    public boolean seed() {
        createProfileAdmin();
        createProfileUser();

        createApiUserAdmin();
        createApiUserClient();

        return true;
    }

    private void createApiUserAdmin() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(adminProfile);
        profiles.add(userProfile);

        ApiUser apiUser = ApiUserBuilder.anApiUser()
                .withId(1)
                .withEmail("admin@email.com")
                .withName("admin")
                .withLogin("admin")
                .withPassword("$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq")
                .withProfiles(profiles)
                .build();

        apiUserRepository.save(apiUser);
    }

    private void createApiUserClient() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(userProfile);

        ApiUser apiUser = ApiUserBuilder.anApiUser()
                .withId(2)
                .withEmail("admin@email.com")
                .withName("client")
                .withLogin("client")
                .withPassword("$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq")
                .build();

        apiUserRepository.save(apiUser);
    }

    private void createProfileAdmin() {
        Profile profile = ProfileBuilder.aProfile()
                .withId(1)
                .withName("ROLE_ADMIN")
                .build();

        adminProfile = profile;
        profileRepository.save(profile);
    }

    private void createProfileUser() {
        Profile profile = ProfileBuilder.aProfile()
                .withId(2)
                .withName("ROLE_USER")
                .build();

        userProfile = profile;
        profileRepository.save(profile);
    }
}
