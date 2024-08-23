//package com.ani.matrimony.controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/upload")
//@CrossOrigin("*")
//public class FileUploadController {
//
//    private static final String UPLOAD_DIR = "uploads/";
//
//    @PostMapping
//    public Map<String, String> uploadFile(@RequestParam("file") MultipartFile file) {
//        Map<String, String> response = new HashMap<>();
//        try {
//            Path filePath = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
//            Files.createDirectories(filePath.getParent());
//            Files.write(filePath, file.getBytes());
//
//            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                    .path("/uploads/")
//                    .path(file.getOriginalFilename())
//                    .toUriString();
//
//            response.put("url", fileDownloadUri);
//        } catch (IOException e) {
//            e.printStackTrace();
//            response.put("error", "File upload failed");
//        }
//        return response;
//    }
//}
