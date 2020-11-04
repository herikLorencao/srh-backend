package com.srh.api.config;

import com.srh.api.builder.*;
import com.srh.api.model.*;
import com.srh.api.repository.*;
import com.srh.api.utils.BcriptyUtil;
import com.srh.api.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DbSeeder {
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private AlgorithmRepository algorithmRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProjectRepository projectRepository;

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
                .withPassword(BcriptyUtil.encripty("123456"))
                .withProfiles(profiles)
                .build();

        apiUserRepository.save(apiUser);
    }

    private void createApiUserClient() {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(userProfile);

        ApiUser apiUser = ApiUserBuilder.anApiUser()
                .withId(2)
                .withEmail("client@email.com")
                .withName("client")
                .withLogin("client")
                .withPassword(BcriptyUtil.encripty("123456"))
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
                .withId(2)
                .withName("Filtragem Baseada em Conteúdo")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm3 = AlgorithmBuilder.anAlgorithm()
                .withId(3)
                .withName("Filtragem Híbrida Ponderada - Single Thread")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm4 = AlgorithmBuilder.anAlgorithm()
                .withId(4)
                .withName("Filtragem Híbrida Mista - Single Thread")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Algorithm algorithm5 = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Híbrida Pondera - Multi Thread")
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
