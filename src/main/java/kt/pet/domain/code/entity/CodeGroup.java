package kt.pet.domain.code.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "code_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeGroup {

    @Id
    @Column(name = "group_id", length = 50, nullable = false)
    private String groupId;  // 그룹 ID (PK)

    @Column(name = "group_name", length = 100, nullable = false)
    private String groupName;  // 그룹명

    @Column(name = "description")
    private String description;  // 설명

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt = LocalDateTime.now();  // 생성 시간

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt = LocalDateTime.now();  // 수정 시간

    @OneToMany(mappedBy = "codeGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CodeDetail> codeDetails;  // CodeDetail과 연관 관계 (1:N)

    public CodeGroup(String groupId, String groupName, String description) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.description = description;
    }


}
