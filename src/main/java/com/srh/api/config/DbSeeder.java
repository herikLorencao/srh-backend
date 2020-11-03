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
    private EvaluatorRepository evaluatorRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TypeItemRepository typeItemRepository;

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private RecommendationRatingRepository recommendationRatingRepository;

    private Profile adminProfile;
    private Profile userProfile;

    public boolean seed() {
        createProfileAdmin();
        createProfileUser();

        createApiUserAdmin();
        createApiUserClient();

        createAlgorithms();
        populateItems();

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

    private void populateItems() {
        PasswordUtil<Admin> adminPasswordUtil = new PasswordUtil<>();
        PasswordUtil<Evaluator> evaluatorPasswordUtil = new PasswordUtil<>();

        Admin admin = AdminBuilder.anAdmin()
                .withId(1)
                .withLogin("adminTest")
                .withName("adminTest")
                .withEmail("admin@email.com")
                .withPassword("123456")
                .build();

        adminRepository.save(adminPasswordUtil.encodedPasswordForUser(admin));

        Evaluator evaluator = EvaluatorBuilder.anEvaluator()
                .withId(1)
                .withLogin("evaluator")
                .withName("evaluator")
                .withEmail("evaluator@email.com")
                .withPassword("123456")
                .build();

        evaluatorRepository.save(evaluatorPasswordUtil.encodedPasswordForUser(evaluator));

        Project project = ProjectBuilder.aProject()
                .withId(1)
                .withAdmin(admin)
                .withDate(LocalDate.now())
                .withDescription("A project")
                .withName("project")
                .withSituation(Situations.OPEN)
                .withVisible(true)
                .withLastMatrixId(1)
                .build();

        projectRepository.save(project);

        TypeItem typeItem = TypeItemBuilder.aTypeItem()
                .withId(1)
                .withName("typeItem 1")
                .build();

        typeItemRepository.save(typeItem);

        Item item = ItemBuilder.anItem()
                .withId(1)
                .withName("Item")
                .withDescription("A item")
                .withProject(project)
                .build();

        itemRepository.save(item);

        Tag tag = TagBuilder.aTag()
                .withId(1)
                .withName("Tag")
                .withItens(Collections.singletonList(item))
                .build();

        tagRepository.save(tag);

        Algorithm algorithm = AlgorithmBuilder.anAlgorithm()
                .withId(1)
                .withName("Filtragem Colaborativa")
                .withTypeRecommendation(TypeRecommendation.COLLABORATIVE)
                .build();

        Recommendation recommendation = RecommendationBuilder.aRecommendation()
                .withId(1)
                .withMatrixId(1)
                .withWeight(0.5)
                .withRuntimeInSeconds(1)
                .withEvaluator(evaluator)
                .withDate(LocalDateTime.now())
                .withItem(item)
                .withAlgorithm(algorithm)
                .build();

        recommendationRepository.save(recommendation);

//        ItemRating itemRating = ItemRatingBuilder.anItemRating()
//                .withItem(item)
//                .withDate(LocalDateTime.now())
//                .withEvaluator(evaluator)
//                .withId(1)
//                .withScore(5.0)
//                .build();

//        itemRatingRepository.save(itemRating);

        RecommendationRating recommendationRating = RecommendationRatingBuilder.aRecommendationRating()
                .withId(1)
                .withRecommendation(recommendation)
                .withDate(LocalDateTime.now())
                .withEvaluator(evaluator)
                .withScore(2.0)
                .build();

        recommendationRatingRepository.save(recommendationRating);
    }
}
