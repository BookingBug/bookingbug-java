package bookingbugAPI2.models.params;

import org.joda.time.DateTime;


public class PurchaseListParams extends Params<PurchaseListParams>{

    DateTime created_from;
    DateTime created_to;
    String admin_bookings_only;
    String order_by;
    String order_by_reverse;

    public DateTime getCreated_from() {
        return created_from;
    }

    public PurchaseListParams setCreated_from(DateTime created_from) {
        this.created_from = created_from;
        return this;
    }

    public DateTime getCreated_to() {
        return created_to;
    }

    public PurchaseListParams setCreated_to(DateTime created_to) {
        this.created_to = created_to;
        return this;
    }

    public String getAdmin_bookings_only() {
        return admin_bookings_only;
    }

    public PurchaseListParams setAdmin_bookings_only(String admin_bookings_only) {
        this.admin_bookings_only = admin_bookings_only;
        return this;
    }

    public String getOrder_by() {
        return order_by;
    }

    public PurchaseListParams setOrder_by(String order_by) {
        this.order_by = order_by;
        return this;
    }

    public String getOrder_by_reverse() {
        return order_by_reverse;
    }

    public PurchaseListParams setOrder_by_reverse(String order_by_reverse) {
        this.order_by_reverse = order_by_reverse;
        return this;
    }
}
