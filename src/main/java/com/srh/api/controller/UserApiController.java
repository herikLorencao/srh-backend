package com.srh.api.controller;

import com.srh.api.dto.resource.UserApiDto;
import com.srh.api.dto.resource.UserForm;
import com.srh.api.hypermedia.UserApiModelAssembler;
import com.srh.api.model.TypeUsers;
import com.srh.api.model.UserApi;
import com.srh.api.service.UserApiService;
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

@RestController
@RequestMapping("/users/api")
public class UserApiController {
    @Autowired
    private UserApiService userApiService;

    @Autowired
    private UserApiModelAssembler userApiModelAssembler;

    @Autowired
    private PagedResourcesAssembler<UserApiDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<UserApiDto>> listAll(@PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<UserApi> users = userApiService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(UserApiDto.convert(users));
    }

    @GetMapping("/{id}")
    public EntityModel<UserApiDto> find(@PathVariable Integer id) {
        UserApi userApi = userApiService.find(id);
        return userApiModelAssembler.toModel(new UserApiDto(userApi));
    }

    @PostMapping
    public ResponseEntity<EntityModel<UserApiDto>> create(@RequestBody @Valid UserForm userForm,
                                                          UriComponentsBuilder uriBuilder) {
        UserApi userApi = (UserApi) userForm.build(TypeUsers.API);
        userApiService.save(userApi);
        URI uri = uriBuilder.path("/users/api/{id}").buildAndExpand(userApi.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(userApiModelAssembler.toModel(new UserApiDto(userApi)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<UserApiDto> update(@RequestBody @Valid UserForm userForm, @PathVariable Integer id) {
        UserApi userApi = (UserApi) userForm.build(TypeUsers.API);
        userApi.setId(id);
        userApi = userApiService.update(userApi);
        return userApiModelAssembler.toModel(new UserApiDto(userApi));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userApiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
