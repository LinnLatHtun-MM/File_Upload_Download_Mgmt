package per.llt.file_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Status status;
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
}
