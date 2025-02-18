package kt.pet.domain.code.dto;

import jakarta.persistence.*;
import kt.pet.domain.code.entity.CodeGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CodeDetailDto {
    private String codeId;
    private String groupId;
    private String codeName;
    private String codeValue;
    private int sortOrder = 1;
    private boolean isActive = true;

    public boolean getIsActive() {
        return isActive;
    }
}
