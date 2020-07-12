package com.srh.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemRating extends Rating {
    @ManyToOne
    private Evaluator evaluator;

    @ManyToOne
    private Item item;
}
