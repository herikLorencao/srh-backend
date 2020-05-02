package com.srh.api.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tag {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "id.tag")
    private List<ItemTag> itensTag;

    public Tag() {
    }

    public Tag(Integer id, String tag, List<ItemTag> itensTag) {
        this.id = id;
        this.name = tag;
        this.itensTag = itensTag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemTag> getItensTag() {
        return itensTag;
    }

    public void setItensTag(List<ItemTag> itensTag) {
        this.itensTag = itensTag;
    }
}
