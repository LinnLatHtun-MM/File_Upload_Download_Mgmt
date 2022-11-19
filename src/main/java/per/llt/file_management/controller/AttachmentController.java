package per.llt.file_management.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import per.llt.file_management.model.Attachment;
import per.llt.file_management.model.Response;
import per.llt.file_management.service.AttachmentService;

/**
 * @author: Linn Lat Htun
 * @created: 18/11/2022
 * @project: uitemplate-restcaller
 * @package: per.llt.file_management.controller
 */

@Slf4j
@RestController
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;


    @PostMapping("/uploadFile")
    public ResponseEntity upload(@RequestParam("file") MultipartFile multipartFile) {

        log.info("====Start Calling FileUpload Method ====");
        Response response = attachmentService.saveAttachment(multipartFile);
        log.info("====Stop Calling FileUpload Method ====");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> download(@PathVariable("id") String fileId) throws Exception {

        log.info("====Start Calling FileDownload Method ====");
        Attachment attachment = attachmentService.getAttachment(fileId);
        //String name = "attachment; filename=\"" + attachment.getFileName() + "\"";
        //System.out.println(name);
        log.info("====Stop Calling FileDownload Method ====");
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
}
