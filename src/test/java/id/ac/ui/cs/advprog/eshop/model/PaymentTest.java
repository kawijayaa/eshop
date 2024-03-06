package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    @Test
    void testCreatePayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Independen");
        paymentData.put("referenceCode", "ADV001");

        Payment payment = new Payment("dd75548a-fade-49f8-8f1b-234a983db403", "Bank Transfer",
                PaymentStatus.SUCCESS.getValue(), paymentData);
        assertEquals("dd75548a-fade-49f8-8f1b-234a983db403", payment.getId());
        assertEquals("Bank Transfer", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertNotNull(payment.getPaymentData());

        Map<String, String> resultPaymentData = payment.getPaymentData();
        assertNotNull(resultPaymentData.get("bankName"));
        assertEquals("Bank Independen", resultPaymentData.get("bankName"));
        assertNotNull(resultPaymentData.get("referenceCode"));
        assertEquals("ADV001", resultPaymentData.get("referenceCode"));
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Independen");
        paymentData.put("referenceCode", "ADV001");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("dd75548a-fade-49f8-8f1b-234a983db403", "Bank Transfer",
                    "ZCZC", paymentData);
        });
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("dd75548a-fade-49f8-8f1b-234a983db403", "Bank Transfer",
                    PaymentStatus.SUCCESS.getValue(), paymentData);
        });
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Independen");
        paymentData.put("referenceCode", "ADV001");

        Payment payment = new Payment("dd75548a-fade-49f8-8f1b-234a983db403", "Bank Transfer",
                PaymentStatus.SUCCESS.getValue(), paymentData);
        payment.setStatus("FAILED");
        assertEquals(PaymentStatus.FAILED.getValue(), payment.getStatus());
    }

    @Test
    void testSetStatusInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Independen");
        paymentData.put("referenceCode", "ADV001");

        Payment payment = new Payment("dd75548a-fade-49f8-8f1b-234a983db403", "Bank Transfer",
                PaymentStatus.SUCCESS.getValue(), paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("ZCZC"));
    }
}
