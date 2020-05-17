package com.srh.api.controller;

import com.srh.api.dto.resource.UserForm;
import com.srh.api.dto.resource.UserRecommendationDto;
import com.srh.api.hypermedia.UserRecommendationModelAssembler;
import com.srh.api.model.TypeUsers;
import com.srh.api.model.UserRecommendation;
import com.srh.api.service.UserRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import static com.srh.api.dto.resource.UserRecommendationDto.convert;

@RestController
@RequestMapping("/users/recommenders")
public class UserRecommendationController {
    @Autowired
    private UserRecommendationService userRecommendationService;

    @Autowired
    private UserRecommendationModelAssembler userRecommendationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<UserRecommendationDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<UserRecommendationDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                          Pageable pageInfo) {
        Page<UserRecommendation> users = userRecommendationService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(users));
    }

    @GetMapping("/{id}")
    public EntityModel<UserRecommendationDto> find(@PathVariable Integer id) {
        UserRecommendation userRecommendation = userRecommendationService.find(id);
        return userRecommendationModelAssembler.toModel(new UserRecommendationDto(userRecommendation));
    }

    @PostMapping
    public ResponseEntity<EntityModel<UserRecommendationDto>> create(@RequestBody @Valid UserForm userForm,
                                                                     UriComponentsBuilder uriBuilder) {
        UserRecommendation user = (UserRecommendation) userForm.build(TypeUsers.RECOMMENDATION);
        userRecommendationService.save(user);
        URI uri = uriBuilder.path("/users/recommenders/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(userRecommendationModelAssembler.toModel(new UserRecommendationDto(user)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<UserRecommendationDto> update(@RequestBody @Valid UserForm userForm,
                                                     @PathVariable Integer id) {
        UserRecommendation user = (UserRecommendation) userForm.build(TypeUsers.RECOMMENDATION);
        user.setId(id);
        user = userRecommendationService.update(user);
        return userRecommendationModelAssembler.toModel(new UserRecommendationDto(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userRecommendationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
