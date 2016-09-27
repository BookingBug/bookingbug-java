package bookingbugAPI2.models.params;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Map;

/**
 * Created by sebi on 29.08.2016.
 */
public class BookingParams {

    public static class List extends Params<List> {
        String start_date;
        String end_date;
        String include_cancelled;
        String event_id;
        String category_id;
        String start_time;
        String modified_since;
        String created_since;
        String email;
        String client_id;
        String order_by;

        public List(int page) {
            super(page);
        }

        public List(int page, int per_page) {
            super(page, per_page);
        }

        public DateTime getStart_date() {
            return new DateTime(start_date);
        }

        public List setStart_date(DateTime start_date) {
            this.start_date = ISODateTimeFormat.dateTime().print(start_date);

            return this;
        }

        public DateTime getEnd_date() {
            return new DateTime(end_date);
        }

        public List setEnd_date(DateTime end_date) {
            this.end_date = ISODateTimeFormat.dateTime().print(end_date);
            return this;
        }

        public Boolean getInclude_cancelled() {
            return Boolean.valueOf(include_cancelled);
        }

        public List setInclude_cancelled(Boolean include_cancelled) {
            this.include_cancelled = include_cancelled.toString();
            return this;
        }

        public Integer getEvent_id() {
            return Integer.valueOf(event_id);
        }

        public List setEvent_id(Integer event_id) {
            this.event_id = event_id.toString();
            return this;
        }

        public Integer getCategory_id() {
            return Integer.valueOf(category_id);
        }

        public List setCategory_id(Integer category_id) {
            this.category_id = category_id.toString();
            return this;
        }

        public DateTime getStart_time() {
            return new DateTime(start_time);
        }

        public List setStart_time(DateTime start_time) {
            this.start_time = ISODateTimeFormat.dateTime().print(start_time);
            return this;
        }

        public DateTime getModified_since() {
            return new DateTime(modified_since);
        }

        public List setModified_since(DateTime modified_since) {
            this.modified_since = ISODateTimeFormat.dateTime().print(modified_since);
            return this;
        }

        public DateTime getCreated_since() {
            return new DateTime(created_since);
        }

        public List setCreated_since(DateTime created_since) {
            this.created_since = ISODateTimeFormat.dateTime().print(created_since);
            return this;
        }

        public String getEmail() {
            return email;
        }

        public List setEmail(String email) {
            this.email = email;
            return this;
        }

        public Integer getClient_id() {
            return Integer.valueOf(client_id);
        }

        public List setClient_id(Integer client_id) {
            this.client_id = client_id.toString();
            return this;
        }

        public String getOrder_by() {
            return order_by;
        }

        public List setOrder_by(String order_by) {
            this.order_by = order_by;
            return this;
        }
    }

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

    public static class Update extends Params<Update> {
        String datetime;
        String duration;
        String person_id;
        String resource_id;
        String email;
        String email_owner;
        String status;

        public DateTime getDatetime() {
            return new DateTime(datetime);
        }

        public String getPerson_id() {
            return person_id;
        }

        public String getResource_id() {
            return resource_id;
        }

        public Update setDatetime(DateTime datetime) {
            this.datetime = ISODateTimeFormat.dateTime().print(datetime);
            return this;
        }

        public Integer getDuration() {
            return Integer.valueOf(duration);
        }

        public Update setDuration(Integer duration) {
            this.duration = duration.toString();
            return this;
        }

        public Update setPerson_id(String person_id) {
            this.person_id = person_id;
            return this;
        }

        public Update setResource_id(String resource_id) {
            this.resource_id = resource_id;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Update setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getEmail_owner() {
            return email_owner;
        }

        public Update setEmail_owner(String email_owner) {
            this.email_owner = email_owner;
            return this;
        }

        public String getStatus() {
            return status;
        }

        public Update setStatus(String status) {
            this.status = status;
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
