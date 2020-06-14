package com.srh.api.dto.resource;

import com.srh.api.model.ItemRating;

import javax.validation.constraints.NotNull;

public class ItemRatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer recommenderId;
    @NotNull
    private Integer itemId;

    public ItemRating build() {
        return null;
    }
}
