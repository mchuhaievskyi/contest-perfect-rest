package ua.kpi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DocumentDto {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String content;
}
