package com.dailycodework.dreamshops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class imageDto {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
