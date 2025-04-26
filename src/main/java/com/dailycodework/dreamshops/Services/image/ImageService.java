package com.dailycodework.dreamshops.Services.image;

import com.dailycodework.dreamshops.Services.ProductService.IProductService;
import com.dailycodework.dreamshops.dto.imageDto;
import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.model.Image;
import com.dailycodework.dreamshops.model.Product;
import com.dailycodework.dreamshops.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final IProductService productService;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No image found with id: " + id));
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () -> {
            throw new ResourceNotFoundException("No image found with id: " + id);
        });
    }

    @Override
    public void updateImage(MultipartFile file, Long imageId) {
        Image image = getImageById(imageId);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public List<imageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<imageDto> savedImageDto = new ArrayList<>();
        for (MultipartFile file1 : files) {
            try {
                Image image = new Image();
                image.setFileName(file1.getOriginalFilename());
                image.setFileType(file1.getContentType());
                image.setImage(new SerialBlob(file1.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/image/image/download/" + image.getId();
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image saveImage = imageRepository.save(image);
                saveImage.setDownloadUrl(buildDownloadUrl + saveImage.getId());
                imageRepository.save(saveImage);

                imageDto imageDto = new imageDto();
                imageDto.setImageId(saveImage.getId());
                imageDto.setImageName(saveImage.getFileName());
                image.setDownloadUrl(saveImage.getDownloadUrl());
                savedImageDto.add(imageDto);
            } catch (IOException | SQLException e1) {
                throw new RuntimeException(e1.getMessage());

            }
        }
        return savedImageDto;
    }
}
