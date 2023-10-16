package io.realworld.angular.conduit.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController {

    //TODO unused
//    @GetMapping("/api/images/{filename:.+}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
//        String filePath = "/Users/ulugbek/downloads/" + filename;
//        Resource resource = new FileSystemResource(filePath);
//
//        // Fayl mavjudligini tekshirish
//        if (!resource.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_JPEG_VALUE)
//                .body(resource);
//    }
//
//
//    @GetMapping("/string")
//    public String string(){
//        return "Hello world";
//    }
}
