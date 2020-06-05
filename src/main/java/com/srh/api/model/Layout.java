package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Layout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fontColor;
    private String fontSecundaryColor;
    private String primaryColor;
    private String menuColor;
    private String backgroundColor;
    private String imageName;

    @OneToOne
    private ApiUser apiUser;
}
