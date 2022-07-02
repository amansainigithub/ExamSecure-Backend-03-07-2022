package com.exam.secure.bucket.bucketController;

import com.exam.secure.bucket.BucketUrlMappings;
import com.exam.secure.bucket.bucketService.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class BucketController {

    @Autowired
    private BucketService serviceBucket;

    @PostMapping(BucketUrlMappings.UPLOAD_FILE)
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file) {
        return ResponseEntity.ok(this.serviceBucket.uploadFile(file));
    }

    @GetMapping(BucketUrlMappings.DOWNLOAD_FILE)
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
        byte[] data = serviceBucket.downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @DeleteMapping(BucketUrlMappings.DELETE_FILE)
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        return new ResponseEntity<>(serviceBucket.deleteFile(fileName), HttpStatus.OK);
    }
}
