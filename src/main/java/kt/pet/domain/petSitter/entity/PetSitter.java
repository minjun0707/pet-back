package kt.pet.domain.petSitter.dto;

import jakarta.persistence.*;
import kt.pet.domain.user.entity.User;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 펫시터 ID

    private String city;  // 활동 가능 지역 (시/도)

    @ElementCollection
    private List<String> availablePets;  // 돌봄 가능한 반려동물 목록

    private LocalDate startDate;  // 돌봄 시작 날짜
    private LocalDate endDate;  // 돌봄 종료 날짜

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> availableDays;  // 돌봄 가능한 요일 (월~일)

    private int startTime;  // 시작 시간 (24시간 형식, 예: 9)
    private int endTime;  // 종료 시간 (24시간 형식, 예: 18)

    private int hourlyRate;  // 시간당 요금 (원)

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}