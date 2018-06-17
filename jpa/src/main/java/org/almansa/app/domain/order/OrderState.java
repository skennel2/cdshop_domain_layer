package org.almansa.app.domain.order;

public enum OrderState {
    Cancel, // 취소 
    OrderWait, // 주문대기
    Ordered, // 주문완료   
    Paid, 
    Prepared, 
    Shipping, 
    Rejected
}
