package per.llt.file_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.llt.file_management.model.Attachment;

import java.util.Optional;

/**
 * @author: Linn Lat Htun
 * @created: 18/11/2022
 * @project: uitemplate-restcaller
 * @package: per.llt.file_management.repo
 */

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {

    Attachment save(Attachment attachment);

    Optional<Attachment> findById(Integer id);
}
