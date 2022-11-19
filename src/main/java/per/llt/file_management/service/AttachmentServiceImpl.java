package per.llt.file_management.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import per.llt.file_management.consts.ErrorMessage;
import per.llt.file_management.model.Attachment;
import per.llt.file_management.model.Response;
import per.llt.file_management.model.Status;
import per.llt.file_management.repo.AttachmentRepository;

/**
 * @author: Linn Lat Htun
 * @created: 18/11/2022
 * @project: uitemplate-restcaller
 * @package: per.llt.file_management.service
 */

@Slf4j
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public Response saveAttachment(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Response response=new Response();
        try {
            if (fileName.equals("") || fileName.contains("..")) {
                response= new Response(failCase(ErrorMessage.FILE_NOT_EXIST), null, null, null, 0);
                log.error("Response: {}",response);
            }
            else {

                Attachment attachment = new Attachment();
                attachment.setFileName(fileName);
                attachment.setFileType(file.getContentType());
                attachment.setData(file.getBytes());

                Attachment att = attachmentRepository.save(attachment);
                if (attachment.getId() != null) {
                    String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/downloadFile/")
                            .path(att.getId().toString())
                            .toUriString();
                    response = new Response(successCase(), att.getFileName(), downloadURL, attachment.getFileType(), file.getSize());
                    log.info("Response: {}",response);
                } else {
                    response= new Response(failCase(ErrorMessage.FILE_SAVE_FAIL), null, null, null, 0);
                    log.error("Response: {}",response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Message: {}",e.getMessage());
            return new Response(failCase(ErrorMessage.INTERNAL_SERVER_ERROR), null, null, null, 0);
        }
        return response;
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {

        return attachmentRepository
                .findById(Integer.parseInt(fileId))
                .orElseThrow(
                        () -> new Exception("File Not Found With Id" + fileId)
                );

        /*Attachment att=new Attachment();
        Optional<Attachment> attachment=attachmentRepository.findById(Integer.parseInt(fileId));

        if(!attachment.isPresent()==false)
        {
            att.setFileName(attachment.get().getFileName());
            att.setFileType(attachment.get().getFileType());
            att.setData(attachment.get().getData());
        }
        return att;*/

    }


    private Status successCase() {
        return new Status(200, "Success", "File is Successfully Saved!");
    }


    private Status failCase(String message) {
        return new Status(500, "Failed", message);
    }

}
