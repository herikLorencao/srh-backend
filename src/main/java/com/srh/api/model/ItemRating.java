package com.srh.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemRating extends Rating {
    @ManyToOne
    private Evaluator user;

    @ManyToOne
    private Item item;
}
