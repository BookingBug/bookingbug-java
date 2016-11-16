package bookingbugAPI2.models.params;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by sebi on 29.08.2016.
 */
public class CompanyParams {

    public static class EventList extends Params<EventList> {
        String start_date;
        String end_date;
        String resource_id;
        String person_id;
        String event_group_id;
        String event_chain_id;
        String summary;
        String member_level_id;
        Boolean embed;
        Boolean include_non_bookable;
        String modified_since;

        public DateTime getStart_date() {
            return new DateTime(start_date);
        }

        public void setStart_date(DateTime start_date) {
            this.start_date = ISODateTimeFormat.dateTime().print(start_date);
        }

        public DateTime getEnd_date() {
            return new DateTime(end_date);
        }

        public void setEnd_date(DateTime end_date) {
            this.end_date = ISODateTimeFormat.dateTime().print(end_date);
        }

        public String getResource_id() {
            return resource_id;
        }

        public void setResource_id(String resource_id) {
            this.resource_id = resource_id;
        }

        public String getPerson_id() {
            return person_id;
        }

        public void setPerson_id(String person_id) {
            this.person_id = person_id;
        }

        public String getEvent_group_id() {
            return event_group_id;
        }

        public void setEvent_group_id(String event_group_id) {
            this.event_group_id = event_group_id;
        }

        public String getEvent_chain_id() {
            return event_chain_id;
        }

        public void setEvent_chain_id(String event_chain_id) {
            this.event_chain_id = event_chain_id;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getMember_level_id() {
            return member_level_id;
        }

        public void setMember_level_id(String member_level_id) {
            this.member_level_id = member_level_id;
        }

        public Boolean getEmbed() {
            return embed;
        }

        public void setEmbed(Boolean embed) {
            this.embed = embed;
        }

        public Boolean getInclude_non_bookable() {
            return include_non_bookable;
        }

        public void setInclude_non_bookable(Boolean include_non_bookable) {
            this.include_non_bookable = include_non_bookable;
        }

        public DateTime getModified_since() {
            return new DateTime(modified_since);
        }

        public void setModified_since(DateTime modified_since) {
            this.modified_since = ISODateTimeFormat.dateTime().print(modified_since);
        }
    }
}
