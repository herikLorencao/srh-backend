package com.srh.api.controller;

import com.srh.api.dto.resource.AdminDto;
import com.srh.api.dto.resource.AdminForm;
import com.srh.api.hypermedia.AdminModelAssembler;
import com.srh.api.model.Admin;
import com.srh.api.service.AdminService;
import lombok.SneakyThrows;
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
@RequestMapping("/users/admins")
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
    public ResponseEntity<EntityModel<AdminDto>> create(@RequestBody @Valid AdminForm adminForm,
                                                        UriComponentsBuilder uriBuilder) {
        Admin admin = adminForm.build();
        adminService.save(admin);
        URI uri = uriBuilder.path("/users/admins/{id}").buildAndExpand(admin.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(adminModelAssembler.toModel(new AdminDto(admin)));
    }

    @PutMapping("/{id}")
    @SneakyThrows
    @Transactional
    public EntityModel<AdminDto> update(@RequestBody @Valid AdminForm adminForm, @PathVariable Integer id) {
        Admin admin = adminForm.build();
        admin.setId(id);
        admin = adminService.update(admin, adminForm.getOldPassword());
        return adminModelAssembler.toModel(new AdminDto(admin));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adminService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
