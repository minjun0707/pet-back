package kt.pet.domain.petSitter.dto;

import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PetSitterAllResponse {

    private Long petSitterId;
    private String city;  // 활동 가능 지역 (시/도)
    private List<String> availablePets;  // 돌봄 가능한 반려동물 목록
    private LocalDate startDate;  // 돌봄 시작 날짜
    private LocalDate endDate;  // 돌봄 종료 날짜
    private List<DayOfWeek> availableDays;  // 돌봄 가능한 요일 (월~일)
    private int startTime;  // 시작 시간 (24시간 형식, 예: 9)
    private int endTime;  // 종료 시간 (24시간 형식, 예: 18)
    private int hourlyRate;  // 시간당 요금 (원)


}
