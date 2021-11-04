package de.test.beanstalk.simple.controller;

import de.test.beanstalk.simple.model.FileRequest;
import de.test.beanstalk.simple.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/files")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addFile(@RequestBody FileRequest request){
        fileService.addFile(request.getName(), request.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body("ok");
    }
}
