package kt.pet.domain.code.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeDetail {

    @Id
    @Column(name = "code_id", length = 50)
    private String codeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @JsonIgnore
    private CodeGroup codeGroup;

    @Column(name = "code_name", length = 100, nullable = false)
    private String codeName;

    @Column(name = "code_value", length = 50, nullable = false)
    private String codeValue;

    @Column(name = "sort_order")
    private int sortOrder = 1;

    @Column(name = "is_active")
    private boolean isActive = true;
}

