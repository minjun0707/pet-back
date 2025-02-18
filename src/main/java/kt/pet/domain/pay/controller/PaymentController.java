package kt.pet.domain.pay.controller;

import kt.pet.domain.pay.entity.Payment;
import kt.pet.domain.pay.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // ✅ 결제 내역 저장
    @PostMapping("/{petSitterId}/{userId}")
    public ResponseEntity<Payment> savePayment(@PathVariable Long petSitterId, @PathVariable Long userId ,@RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.savePayment(payment,petSitterId,userId));
    }

    // ✅ 특정 userId로 조회
    @GetMapping("/{userId}")
    public ResponseEntity<Payment> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.findByUserId(userId));
    }
//
//    // ✅ 특정 펫시터의 결제 내역 조회
//    @GetMapping("/petsitter/{petsitterId}")
//    public ResponseEntity<List<Payment>> getPaymentsByPetsitter(@PathVariable Long petsitterId) {
//        return ResponseEntity.ok(paymentService.findByPetsitterId(petsitterId));
//    }
}