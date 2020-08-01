package com.srh.api.controller;

import com.srh.api.dto.resource.RecommendationResultMatrixDto;
import com.srh.api.dto.resource.RecommendationResultMatrixForm;
import com.srh.api.model.RecommendationResultMatrix;
import com.srh.api.service.RecommendationResultMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/recommendationmatrices")
public class RecommendationResultMatrixController {
    @Autowired
    private RecommendationResultMatrixService recommendationResultMatrixService;

    @GetMapping("/{id}")
    public EntityModel<RecommendationResultMatrix> find(@PathVariable  Integer id) {
        return null;
    }

    @GetMapping
    public PagedModel<EntityModel<RecommendationResultMatrixDto>> listAll(
            @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        return null;
    }

    @PostMapping
    public ResponseEntity<RecommendationResultMatrixDto> calculateRecommendation(
            @RequestBody @Valid RecommendationResultMatrixForm form) {
        RecommendationResultMatrix recommendationResultMatrix = recommendationResultMatrixService
                .generateRecommendation(form.build());
        return ResponseEntity.ok(new RecommendationResultMatrixDto(recommendationResultMatrix));
    }
}
