package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class BookingCancelParams extends Params {

    String notify = "true";
    String cancel_reason;

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getCancelReason() {
        return cancel_reason;
    }

    public void setCancelReason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }
}
