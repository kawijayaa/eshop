package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("c4b28272-9fe1-4f8c-9c88-da69fb7ae6eb", "VOUCHER_CODE", "SUCCESS", paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank Independen");
        paymentData2.put("referenceCode", "ADV001");
        Payment payment2 = new Payment("6789d762-91a3-4560-875b-16fa1937db09", "BANK_TRANSFER", "SUCCESS", paymentData2);
        payments.add(payment2);
    }

    @Test
    void testSavePaymentNew() {
        Payment payment = payments.getFirst();
        Payment saveResult = paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getId(), saveResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testSavePaymentUpdate() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Payment newPayment = new Payment(payment.getId(), payment.getMethod(), "FAILED", payment.getPaymentData());
        Payment updatedResult = paymentRepository.save(newPayment);
        Payment findResult = paymentRepository.findById(payment.getId());

        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getId(), updatedResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(updatedResult.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdFound() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(payment.getMethod(), findResult.getMethod());
        assertEquals(payment.getStatus(), findResult.getStatus());
        assertEquals(payment.getPaymentData(), findResult.getPaymentData());
    }

    @Test
    void testFindByIdNotFound() {
        Payment payment = payments.getFirst();
        paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testFindAll() {
        for (Payment payment: payments) {
            paymentRepository.save(payment);
        }

        List<Payment> findResult = new ArrayList<>();
        paymentRepository.findAll().forEachRemaining(findResult::add);
        assertEquals(payments.size(), findResult.size());
    }

    @Test
    void testFindAllEmpty() {
        List<Payment> findResult = new ArrayList<>();
        paymentRepository.findAll().forEachRemaining(findResult::add);
        assertEquals(0, findResult.size());
    }
}
