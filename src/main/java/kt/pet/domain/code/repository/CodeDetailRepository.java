package kt.pet.domain.code.repository;

import kt.pet.domain.code.entity.CodeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, String> {
    List<CodeDetail> findByCodeGroup_GroupId(String groupId);  // 특정 그룹의 코드 상세 조회
}