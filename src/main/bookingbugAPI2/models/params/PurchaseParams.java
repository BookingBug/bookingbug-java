package bookingbugAPI2.models.params;


public class PurchaseParams extends Params<PurchaseParams>{
    Integer amount;
    String notes;
    String paymentStatus;
    String transactionId;
    Boolean notify;
    Boolean notifyAdmin;
    Integer paymentType;

    public Integer getAmount() {
        return amount;
    }

    public PurchaseParams setAmount(Integer amount) {
        this.amount = amount;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public PurchaseParams setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public PurchaseParams setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PurchaseParams setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public Boolean getNotify() {
        return notify;
    }

    public PurchaseParams setNotify(Boolean notify) {
        this.notify = notify;
        return this;
    }

    public Boolean getNotifyAdmin() {
        return notifyAdmin;
    }

    public PurchaseParams setNotifyAdmin(Boolean notifyAdmin) {
        this.notifyAdmin = notifyAdmin;
        return this;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public PurchaseParams setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
        return this;
    }
}
