package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("c4b28272-9fe1-4f8c-9c88-da69fb7ae6eb",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("6789d762-91a3-4560-875b-16fa1937db09",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment("c4b28272-9fe1-4f8c-9c88-da69fb7ae6eb", "VOUCHER_CODE",
                "SUCCESS", paymentData1);
        payments.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank Independen");
        paymentData2.put("referenceCode", "ADV001");
        Payment payment2 = new Payment("6789d762-91a3-4560-875b-16fa1937db09", "BANK_TRANSFER",
                "SUCCESS", paymentData2);
        payments.add(payment2);
    }

    @Test
    void testAddPaymentBankTransferValid() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(orders.get(1), payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testAddPaymentBankTransferInvalidPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(orders.getFirst(), "BANK_TRANSFER", new HashMap<>());
        });
    }

    @Test
    void testAddPaymentVoucherValid() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(orders.getFirst(), payment.getMethod(), payment.getPaymentData());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testAddPaymentVoucherInvalidCode() {
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("voucherCode", "ESHOPABCDEF1234");

        Payment result = paymentService.addPayment(orders.getFirst(), "VOUCHER_CODE", invalidPaymentData);
        assertEquals("FAILED", result.getStatus());
    }
}
