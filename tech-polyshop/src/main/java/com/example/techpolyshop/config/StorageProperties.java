package com.example.techpolyshop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties("storage")
@Data
public class StorageProperties {
    // luu vi tri hinh duoc upload len server
    private String location;
}
