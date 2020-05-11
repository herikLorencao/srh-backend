package com.srh.api.service;

import com.srh.api.model.Admin;
import com.srh.api.repository.AdminRepository;
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
        return adminRepository.save(admin);
    }

    public Admin update(Admin admin) {
        find(admin.getId());
        return adminRepository.save(admin);
    }

    public void delete(Integer id) {
        find(id);
        adminRepository.deleteById(id);
    }
}
