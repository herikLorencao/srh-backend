package com.srh.api.service;

import com.srh.api.model.TypeRecommendation;
import com.srh.api.repository.TypeRecommendationRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TypeRecommendationService {
    @Autowired
    private TypeRecommendationRepository typeRecommendationRepository;

    public TypeRecommendation find(Integer id) {
        Optional<TypeRecommendation> typeRecommendation = typeRecommendationRepository.findById(id);

        if (typeRecommendation.isPresent())
            return typeRecommendation.get();

        throw new ObjectNotFoundException(id, TypeRecommendation.class.getName());
    }

    public Page<TypeRecommendation> findAll(Pageable pageInfo) {
        return typeRecommendationRepository.findAll(pageInfo);
    }

    public TypeRecommendation save(TypeRecommendation typeRecommendation) {
        return typeRecommendationRepository.save(typeRecommendation);
    }

    public TypeRecommendation update(TypeRecommendation typeRecommendation) {
        find(typeRecommendation.getId());
        return typeRecommendationRepository.save(typeRecommendation);
    }

    public void delete(Integer id) {
        find(id);
        typeRecommendationRepository.deleteById(id);
    }
}
