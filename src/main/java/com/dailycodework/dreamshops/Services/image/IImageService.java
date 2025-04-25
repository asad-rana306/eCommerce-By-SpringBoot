package com.dailycodework.dreamshops.Services.image;

import com.dailycodework.dreamshops.model.Image;
import com.dailycodework.dreamshops.model.Product;
import org.springframework.web.multipart.MultipartFile;
import com.dailycodework.dreamshops.dto.imageDto;
import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImage(Long id);
    void updateImage(MultipartFile file, Long imageId);
    List<imageDto> saveImages(List<MultipartFile> files, Long productId);
    //this is a interface which takes promise like who implements it
    // must have this method
}
