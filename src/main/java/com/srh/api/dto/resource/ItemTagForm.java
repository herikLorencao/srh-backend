package com.srh.api.dto.resource;

import javax.validation.constraints.NotNull;

public class ItemTagForm {
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer tagId;
}
