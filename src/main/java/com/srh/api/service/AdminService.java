package com.srh.api.service;

import com.srh.api.model.Admin;
import com.srh.api.repository.AdminRepository;
import com.srh.api.utils.PasswordUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    private PasswordUtil<Admin> passwordUtil = new PasswordUtil<Admin>();

    public Admin find(Integer id) {
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isPresent())
            return admin.get();

        throw new ObjectNotFoundException(id, Admin.class.getName());
    }

    public Page<Admin> findAll(Pageable pageInfo) {
        return adminRepository.findAll(pageInfo);
    }

    public Admin save(Admin admin) {
        Admin adminEncoded = passwordUtil.encodedPasswordForUser(admin);
        return adminRepository.save(adminEncoded);
    }

    @SneakyThrows
    public Admin update(Admin admin, String oldRawPassword) {
        Admin oldAdmin = find(admin.getId());
        Admin persistAdmin = passwordUtil.verifyPasswordChanges(admin, oldAdmin, oldRawPassword);
        persistAdmin.setProjects(oldAdmin.getProjects());
        return adminRepository.save(persistAdmin);
    }

    public void delete(Integer id) {
        find(id);
        adminRepository.deleteById(id);
    }
}
