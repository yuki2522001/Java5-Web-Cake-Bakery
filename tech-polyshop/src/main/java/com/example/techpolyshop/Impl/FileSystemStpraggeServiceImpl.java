package com.example.techpolyshop.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.example.techpolyshop.config.StorageProperties;
import com.example.techpolyshop.exception.StorageException;
import com.example.techpolyshop.exception.StorageFoundNotFoundException;
import com.example.techpolyshop.service.StorageService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStpraggeServiceImpl implements StorageService{
    private final Path rootLocation;

    // luu thong tin va lay thong tin ve ten file duoc luu tru
    public String getStoredFilename(MultipartFile file, String id){
        // xd file img la jpg or png           
                                             // tra ve ten file duoc upload toi server
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        return "p" + id + "." + ext;
    }
    // cac cau hinh cho phep luu tru thong tin
    public FileSystemStpraggeServiceImpl(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }
    // phuong thuc dung de luu thong tin
    public void store(MultipartFile file, String storeFilename){
        try {
            if (file.isEmpty()){
                throw new StorageException("Failed to store empty file");
            }
            // chuan hoa normalize chuyen duong dan thanh tuyet doi
            Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename))
                .normalize().toAbsolutePath();
                // kiem tra co bang nhau ko
            if(!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())){
                // khong thi nem ra
                throw new StorageException("Cannot store file outside current directory");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);   
            }
        } catch (Exception e) {
            throw new StorageException("Failed to store file" ,e);
        }
    }
    // nap cac tai nguyen
    public Resource loadAsResource(String filename){
        try {
            // goi thuc hien phuong thuc load
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            throw new StorageFoundNotFoundException("Could not read file: " + filename);
        } catch (Exception e) {
            throw new StorageFoundNotFoundException("Could not read file: " + filename);
        }
    }
    public Path load(String filename){
        return rootLocation.resolve(filename);
    }
    public void delete(String storeFilename) throws IOException{
        Path destinationFile = rootLocation.resolve(Paths.get(storeFilename)).normalize().toAbsolutePath();
        Files.delete(destinationFile);
    }
    public void init(){
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }


}
