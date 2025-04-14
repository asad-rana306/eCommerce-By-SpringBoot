package com.dailycodework.dreamshops.Services.image;

import com.dailycodework.dreamshops.model.Image;
import com.dailycodework.dreamshops.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImage(Long id);
    void updateImage(MultipartFile file, Long imageId);
    Image saveImage(List<MultipartFile> files, Long productId);
}
