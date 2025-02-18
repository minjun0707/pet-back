package kt.pet.domain.code.service;

import kt.pet.domain.code.dto.CodeGroupDto;
import kt.pet.domain.code.entity.CodeGroup;
import kt.pet.domain.code.repository.CodeGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CodeGroupService {

    private final CodeGroupRepository codeGroupRepository;

    // 전체 코드 그룹 조회
    public List<CodeGroup> getAllCodeGroups() {
        return codeGroupRepository.findAll();
    }

    // 코드 그룹 저장
    public CodeGroup saveCodeGroup(CodeGroupDto codeGroup) {
        return codeGroupRepository.save(new CodeGroup(codeGroup.getGroupId(), codeGroup.getGroupName(), codeGroup.getDescription()));
    }

    // 코드 그룹 수정
    @Transactional
    public CodeGroup updateCodeGroup(String id, CodeGroup updatedGroup) {
        Optional<CodeGroup> existingGroup = codeGroupRepository.findById(id);
        if (existingGroup.isPresent()) {
            CodeGroup group = existingGroup.get();
            group.setGroupName(updatedGroup.getGroupName());
            group.setDescription(updatedGroup.getDescription());
            return codeGroupRepository.save(group);
        } else {
            throw new RuntimeException("CodeGroup not found");
        }
    }

    // 코드 그룹 삭제
    public void deleteCodeGroup(String id) {
        codeGroupRepository.deleteById(id);
    }
}
