package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String[] paymentMethods = {"VOUCHER_CODE", "BANK_TRANSFER"};
        String status = "SUCCESS";
        if (Arrays.stream(paymentMethods).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        }
        if (method.equals("VOUCHER_CODE") && !isValidVoucher(paymentData.get("voucherCode"))) {
            status = "FAILED";
        }
        if (method.equals("BANK_TRANSFER") && (paymentData.get("bankName") == null || paymentData.get("referenceCode") == null)) {
            status = "FAILED";
        }

        Payment payment = new Payment(order.getId(), method, status, paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (PaymentStatus.contains(status)) {
            payment.setStatus(status);
            Order order = orderRepository.findById(payment.getId());
            order.setStatus(status);
            return payment;
        }

        return null;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public Iterator<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public boolean isValidVoucher(String voucher) {
        boolean isLengthCorrect = voucher.length() == 16;
        boolean isStartWithESHOP = voucher.startsWith("ESHOP");
        int digitCount = 0;
        for (int i = 0; i < voucher.length(); i++) {
            if (Character.isDigit(voucher.charAt(i))) {
                digitCount++;
            }
        }
        boolean isDigitCount8 = digitCount == 8;

        return isLengthCorrect && isStartWithESHOP && isDigitCount8;
    }
}

