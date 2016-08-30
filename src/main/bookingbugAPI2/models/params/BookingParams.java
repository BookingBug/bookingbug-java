package bookingbugAPI2.models.params;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Map;

/**
 * Created by sebi on 29.08.2016.
 */
public class BookingParams {

    public static class Create extends Params<Create> {
        String datetime;
        String service_id;
        String person_id;
        String resource_id;
        String member_id;
        String notifications;

        public DateTime getDatetime() {
            return new DateTime(datetime);
        }


        public String getService_id() {
            return service_id;
        }



        public String getPerson_id() {
            return person_id;
        }


        public String getResource_id() {
            return resource_id;
        }


        public String getMember_id() {
            return member_id;
        }


        public Boolean getNotifications() {
            return Boolean.valueOf(notifications);
        }

        public Create setDatetime(DateTime datetime) {
            this.datetime = ISODateTimeFormat.dateTime().print(datetime);
            return this;
        }

        public Create setService_id(String service_id) {
            this.service_id = service_id;
            return this;
        }

        public Create setPerson_id(String person_id) {
            this.person_id = person_id;
            return this;
        }

        public Create setResource_id(String resource_id) {
            this.resource_id = resource_id;
            return this;
        }

        public Create setMember_id(String member_id) {
            this.member_id = member_id;
            return this;
        }

        public Create setNotifications(Boolean notifications) {
            this.notifications = notifications.toString();
            return this;
        }
    }

    public static class Cancel extends Params<Cancel> {
        String notify = "true";
        String cancel_reason;

        public Boolean getNotify() {
            return Boolean.valueOf(notify);
        }

        public Cancel setNotify(Boolean notify) {
            this.notify = notify.toString();
            return this;
        }

        public String getCancelReason() {
            return cancel_reason;
        }

        public Cancel setCancelReason(String cancel_reason) {
            this.cancel_reason = cancel_reason;
            return this;
        }
    }
}
