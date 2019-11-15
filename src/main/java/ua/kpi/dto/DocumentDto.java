package ua.kpi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DocumentDto {

    @NotBlank
    private String name;

    @NotBlank
    private String content;
}
