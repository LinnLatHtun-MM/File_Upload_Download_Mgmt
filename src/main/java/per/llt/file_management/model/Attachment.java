package per.llt.file_management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author: Linn Lat Htun
 * @created: 18/11/2022
 * @project: uitemplate-restcaller
 * @package: per.llt.file_management.model
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "attachment")
public class Attachment {

    @Id
    @Column(name = "att_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    /**
     * The @Lob annotation is used to specify that the currently annotated entity attribute represents a large object type.
     **/
    @Lob
    @Column(name = "data")
    private byte[] data;
}
