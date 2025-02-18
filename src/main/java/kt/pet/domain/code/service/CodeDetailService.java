package kt.pet.domain.code.service;

import kt.pet.domain.code.dto.CodeDetailDto;
import kt.pet.domain.code.entity.CodeDetail;
import kt.pet.domain.code.entity.CodeGroup;
import kt.pet.domain.code.repository.CodeDetailRepository;
import kt.pet.domain.code.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CodeDetailService {

    private final CodeDetailRepository codeDetailRepository;
    private final CodeGroupRepository codeGroupRepository;

    // 특정 그룹의 코드 상세 목록 조회
    public List<CodeDetail> getCodeDetailsByGroupId(String groupId) {
        return codeDetailRepository.findByCodeGroup_GroupId(groupId);
    }

    // 코드 상세 저장
    public CodeDetail saveCodeDetail(CodeDetailDto codeDetailDto) {
        CodeGroup group = codeGroupRepository.findById(codeDetailDto.getGroupId()).orElseThrow();
        return codeDetailRepository.save(new CodeDetail(codeDetailDto.getCodeId(),group,codeDetailDto.getCodeName(),codeDetailDto.getCodeValue(),codeDetailDto.getSortOrder(),codeDetailDto.getIsActive()));
    }

    // 코드 상세 수정
    public CodeDetail updateCodeDetail(String id, CodeDetail updatedDetail) {
        Optional<CodeDetail> existingDetail = codeDetailRepository.findById(id);
        if (existingDetail.isPresent()) {
            CodeDetail detail = existingDetail.get();
            detail.setCodeName(updatedDetail.getCodeName());
            detail.setCodeValue(updatedDetail.getCodeValue());
            detail.setSortOrder(updatedDetail.getSortOrder());
            detail.setActive(updatedDetail.isActive());
            return codeDetailRepository.save(detail);
        } else {
            throw new RuntimeException("CodeDetail not found");
        }
    }

    // 코드 상세 삭제
    public void deleteCodeDetail(String id) {
        codeDetailRepository.deleteById(id);
    }
}
