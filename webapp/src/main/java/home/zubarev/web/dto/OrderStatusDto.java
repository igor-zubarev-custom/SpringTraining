package home.zubarev.web.dto;

public class OrderStatusDto {
    private Long orderId;
    private int statusNumber;

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public int getStatusNumber() {
        return statusNumber;
    }
    public void setStatusNumber(int statusNumber) {
        this.statusNumber = statusNumber;
    }
}
