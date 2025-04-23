package com.dailycodework.dreamshops.dto;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class imageDto {
    private Long imageId;
    private String imageName;
    private String downloadUrl;
}
