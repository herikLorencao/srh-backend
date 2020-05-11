package com.srh.api.controller;

import com.srh.api.dto.resource.AdminDto;
import com.srh.api.dto.resource.UserForm;
import com.srh.api.hypermedia.AdminModelAssembler;
import com.srh.api.model.Admin;
import com.srh.api.model.TypeUsers;
import com.srh.api.service.AdminService;
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

import static com.srh.api.dto.resource.AdminDto.convert;

@RestController
@RequestMapping("/users/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminModelAssembler adminModelAssembler;

    @Autowired
    private PagedResourcesAssembler<AdminDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<AdminDto>> listAll(@PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Admin> admins = adminService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(admins));
    }

    @GetMapping("/{id}")
    public EntityModel<AdminDto> find(@PathVariable Integer id) {
        Admin admin = adminService.find(id);
        return adminModelAssembler.toModel(new AdminDto(admin));
    }

    @PostMapping
    public ResponseEntity<EntityModel<AdminDto>> create(@RequestBody @Valid UserForm userForm,
                                                        UriComponentsBuilder uriBuilder) {
        Admin admin = (Admin) userForm.build(TypeUsers.ADMIN);
        adminService.save(admin);
        URI uri = uriBuilder.path("/tags/{id}").buildAndExpand(admin.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(adminModelAssembler.toModel(new AdminDto(admin)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<AdminDto> update(@RequestBody @Valid UserForm userForm, @PathVariable Integer id) {
        Admin admin = (Admin) userForm.build(TypeUsers.ADMIN);
        admin.setId(id);
        admin = adminService.update(admin);
        return adminModelAssembler.toModel(new AdminDto(admin));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
