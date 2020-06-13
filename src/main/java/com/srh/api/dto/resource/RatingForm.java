package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import com.srh.api.model.Evaluator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
