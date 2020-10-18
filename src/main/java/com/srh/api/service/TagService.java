package com.srh.api.service;

import com.srh.api.model.Tag;
import com.srh.api.repository.TagRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public Tag find(Integer id) {
        Optional<Tag> tag = tagRepository.findById(id);

        if (tag.isPresent())
            return tag.get();

        throw new ObjectNotFoundException(id, Tag.class.getName());
    }

    public Page<Tag> findAll(Pageable pageInfo) {
        return tagRepository.findAll(pageInfo);
    }

    public List<Tag> findAll() {
        return (List<Tag>) tagRepository.findAll();
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Tag tag) {
        find(tag.getId());
        return tagRepository.save(tag);
    }

    public void delete(Integer id) {
        find(id);
        tagRepository.deleteById(id);
    }
}
