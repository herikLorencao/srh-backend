package com.srh.api.controller;

import com.srh.api.algorithms.resources.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationDto;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.dto.resource.RecommendationsByEvaluatorDto;
import com.srh.api.hypermedia.RecommendationModelAssembler;
import com.srh.api.hypermedia.RecommendationsByEvaluatorModelAssembler;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import com.srh.api.service.RecommendationService;
import com.srh.api.utils.PageUtil;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import static com.srh.api.dto.resource.RecommendationDto.convert;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationModelAssembler recommendationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationsByEvaluatorDto> recommendationsByEvaluatorModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RecommendationDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                      Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(recommendations));
    }

    @GetMapping("/{id}")
    public EntityModel<RecommendationDto> find(@PathVariable Integer id) {
        Recommendation recommendation = recommendationService.find(id);
        return recommendationModelAssembler.toModel(new RecommendationDto(recommendation));
    }

    @PostMapping
//    PagedModel<EntityModel<RecommendationsByEvaluatorDto>> create(
    ResponseEntity<?> create(
            @RequestBody @Valid RecommendationForm recommendationForm,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        return ResponseEntity.ok(recommendationService.generateRecommendations(recommendationForm));
//        List<RecommendationsByEvaluator> recommendationsByEvaluatorList = recommendationService.
//                generateRecommendations(recommendationForm);
//
//        PageUtil<RecommendationsByEvaluator> pageUtil = new PageUtil<>(pageInfo,
//                recommendationsByEvaluatorList);
//
//        return recommendationsByEvaluatorModelAssembler.toModel(RecommendationsByEvaluatorDto.convert(pageUtil.getPage()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recommendationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/performances/{algorithmId}")
    public ResponseEntity<Page<RecommendationDto>> listRecommendationsPerformance(
            @PathVariable Integer algorithmId, Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.listRecommendationsByAlgorithm(
                algorithmId, pageInfo
        );
        return ResponseEntity.ok(RecommendationDto.convert(recommendations));
    }

    @GetMapping("/matrices/{matrixId}")
    public ResponseEntity<Page<RecommendationDto>> listRecommendationsMatrix(
            @PathVariable Integer matrixId, Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.listRecommendationsByMatrixId(
                matrixId, pageInfo);
        return ResponseEntity.ok(RecommendationDto.convert(recommendations));
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Page<RecommendationDto>> listRecommendationsByTag(
            @PathVariable Integer tagId, Pageable pageInfo
    ) {
        Page<Recommendation> recommendations = recommendationService.listRecommendationsByTag(tagId, pageInfo);
        return ResponseEntity.ok(RecommendationDto.convert(recommendations));
    }
}
