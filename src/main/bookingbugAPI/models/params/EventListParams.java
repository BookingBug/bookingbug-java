package bookingbugAPI.models.params;

/**
 * Created by sebi on 31.03.2016.
 */
public class EventListParams extends Params {
    String resource_id;
    String event_chain_id;
    String person_id;
    String event_group_id;
    String summary;
    String member_level_id;
    String start_date;
    String end_date;

    public EventListParams() {}

    public EventListParams(int page){
        super(page);
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getEvent_chain_id() {
        return event_chain_id;
    }

    public void setEvent_chain_id(String event_chain_id) {
        this.event_chain_id = event_chain_id;
    }
}
