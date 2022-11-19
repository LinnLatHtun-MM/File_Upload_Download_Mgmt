package per.llt.file_management.service;

import org.springframework.web.multipart.MultipartFile;
import per.llt.file_management.model.Attachment;
import per.llt.file_management.model.Response;


/**
 * @author: Linn Lat Htun
 * @created: 18/11/2022
 * @project: uitemplate-restcaller
 * @package: per.llt.file_management.service
 */

public interface AttachmentService {
    Response saveAttachment(MultipartFile file);

    Attachment getAttachment(String fileId) throws Exception;

}
