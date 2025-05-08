package com.dailycodework.dreamshops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class imageDto {
    private Long id;
    private String fileName;
    private String downloadUrl;
}
