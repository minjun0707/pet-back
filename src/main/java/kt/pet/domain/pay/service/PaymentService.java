package kt.pet.domain.pay.service;

import kt.pet.domain.pay.entity.Payment;
import kt.pet.domain.user.entity.User;
import kt.pet.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Payment savePayment(Payment payment, Long petSitterId, Long userId) {
        payment.setUserId(userId);
        payment.setPetSitterId(petSitterId);

        return paymentRepository.save(payment);
    }

    public Payment findByUserId(Long userId) {
        Payment payment = paymentRepository.findByUserId(userId).get(0);
        System.out.println(payment.toString());
        return payment;
    }

//
//    public List<Payment> findByPetsitterId(Long petsitterId) {
//        return paymentRepository.findByPetsitterId(petsitterId);
//    }
}
