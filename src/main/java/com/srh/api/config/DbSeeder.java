package com.srh.api.config;

import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.builder.ApiUserBuilder;
import com.srh.api.builder.ProfileBuilder;
import com.srh.api.model.Algorithm;
import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;
import com.srh.api.model.TypeRecommendation;
import com.srh.api.repository.AlgorithmRepository;
import com.srh.api.repository.ApiUserRepository;
import com.srh.api.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DbSeeder {
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AlgorithmRepository algorithmRepository;

    private Profile adminProfile;
    private Profile userProfile;

    public boolean seed() {
        createProfileAdmin();
        createProfileUser();

        createApiUserAdmin();
        createApiUserClient();

        createAlgorithms();

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
                .withProfiles(profiles)
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

    private void createAlgorithms() {
        Algorithm algorithm1 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Colaborativa")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm2 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Baseada em Conteúdo")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm3 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Híbrida ponderada - Single Thread")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm4 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Híbrida ponderada - Multi Thread")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm5 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Híbrida Mista - Single Thread")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm6 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Híbrida Mista - Multi Thread")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        algorithmRepository.saveAll(Arrays.asList(
                algorithm1, algorithm2, algorithm3,
                algorithm4, algorithm5, algorithm6
        ));
    }
}
