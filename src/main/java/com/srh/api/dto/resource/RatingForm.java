package com.srh.api.dto.resource;

import com.srh.api.model.ItemRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer recommenderId;
    @NotNull
    private Integer itemId;

    public ItemRating build() {
//        Evaluator evaluator = Eval
//                .withId(recommenderId)
//                .build();
//
//        Item item = ItemBuilder.anItem()
//                .withId(itemId)
//                .build();
//
//        return RatingBuilder.aRating()
//                .withScore(score)
//                .withUser(evaluator)
//                .withItem(item)
//                .withDate(LocalDateTime.now())
//                .build();
        return null;
    }
}
