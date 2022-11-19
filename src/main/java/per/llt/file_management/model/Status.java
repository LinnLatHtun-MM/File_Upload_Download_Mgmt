package per.llt.file_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Linn Lat Htun
 * @created: 18/11/2022
 * @project: uitemplate-restcaller
 * @package: per.llt.file_management.model
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private int statusCode;
    private String statusMessage;
    private String description;
}
