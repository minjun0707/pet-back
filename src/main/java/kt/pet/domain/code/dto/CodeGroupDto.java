package kt.pet.domain.code.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CodeGroupDto {
    private String groupId;  // 그룹 ID (PK)
    private String groupName;  // 그룹명
    private String description;  // 설명
}
