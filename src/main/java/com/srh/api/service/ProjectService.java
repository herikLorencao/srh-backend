package com.srh.api.service;

import com.srh.api.error.exception.ChangeRootRelationException;
import com.srh.api.model.Admin;
import com.srh.api.model.Project;
import com.srh.api.repository.ProjectRepository;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AdminService adminService;

    public Project find(Integer id) {
        Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent())
            return project.get();

        throw new ObjectNotFoundException(id, Project.class.getName());
    }

    public Page<Project> findAll(Pageable pageInfo) {
        return projectRepository.findAll(pageInfo);
    }

    public Project save(Project project) {
        Integer adminId = project.getAdmin().getId();
        Admin admin = adminService.find(adminId);
        project.setAdmin(admin);
        return projectRepository.save(project);
    }

    @SneakyThrows
    public Project update(Project project) {
        Project oldProject = find(project.getId());

        if (project.getAdmin().equals(oldProject.getAdmin())) {
            throw new ChangeRootRelationException("Correct value: " + oldProject.getAdmin().getId());
        }

        project.setItens(oldProject.getItens());
        return projectRepository.save(project);
    }

    public void delete(Integer id) {
        find(id);
        projectRepository.deleteById(id);
    }
}
