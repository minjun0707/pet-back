package kt.pet.domain.pay.entity;

import jakarta.persistence.*;
import kt.pet.domain.user.entity.User;
import lombok.*;

@Entity
@Table(name = "payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본키 (자동 생성)

    @Column(unique = true, nullable = false)
    private String impUid; // 아임포트 결제 고유 ID

    @Column(unique = true, nullable = false)
    private String merchantUid; // 주문 번호 (가맹점 고유번호)

    private String applyNum; // 승인번호
    private String bankName; // 은행 이름 (무통장입금일 경우)
    private String cardName; // 카드사 이름
    private String cardNumber; // 카드번호 (일부 마스킹)
    private Integer cardQuota; // 할부 개월 수 (0이면 일시불)

    private String buyerName; // 구매자 이름
    private String buyerTel; // 구매자 전화번호
    private String buyerEmail; // 구매자 이메일
    private String buyerAddr; // 구매자 주소
    private String buyerPostcode; // 구매자 우편번호

    private String currency; // 통화 (KRW 등)
    private Integer paidAmount; // 결제 금액
    private Long paidAt; // 결제 완료 시각 (Unix Timestamp)

    private String payMethod; // 결제 방법 (카드, 계좌이체 등)
    private String pgProvider; // PG사
    private String pgTid; // PG사 거래번호

    private String receiptUrl; // 영수증 URL
    private String status; // 결제 상태 (paid, failed 등)

    private Long userId;
    private Long petSitterId;
}
