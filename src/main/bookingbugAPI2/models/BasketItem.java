package bookingbugAPI2.models;

import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import helpers2.HttpServiceResponse;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class BasketItem extends BBRoot {


    public BasketItem(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    /**
     * Returns the event_id.
     *
     * @return The event_id associated with the current BasketItemsTwo object
     */
    public Integer getEventId() {
        return getInteger("event_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the person id.
     *
     * @return The person id associated with the current BasketItemsTwo object
     */
    public Integer getPersonId() {
        return getInteger("person_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the service id.
     *
     * @return The service id associated with the current BasketItemsTwo object
     */
    public Integer getServiceId() {
        return getInteger("service_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the price.
     *
     * @return The price associated with the current BasketItemsTwo object
     */
    public Integer getPrice() {
        return getInteger("price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the number of bookings.
     *
     * @return The number of bookings associated with the current BasketItemsTwo object
     */
    public Integer getNumBook() {
        return getInteger("num_book", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the person's name.
     *
     * @return The person's name associated with the current BasketItemsTwo object
     */
    public String getPersonName() {
        return get("person_name");
    }

    /**
     * Returns the service's name.
     *
     * @return The service's name associated with the current BasketItemsTwo object
     */
    public String getServiceName() {
        return get("service_name");
    }

    /**
     * Returns the status.
     *
     * @return The status associated with the current BasketItemsTwo object
     */
    public Integer getStatus() {
        return getInteger("status", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the id.
     *
     * @return The id associated with the current BasketItemsTwo object
     */
    public String getId() {
        return get("id");
    }

    /**
     * Returns the date and time, with {@link DateTime DateTime()} as format.
     *
     * @return The date and time associated with the current BasketItemsTwo object
     */
    public DateTime getDate() {
        return getDate("date");
    }

    /**
     * Returns the time.
     *
     * @return The time  associated with the current BasketItemsTwo object
     */
    public Integer getTime() {
        return getInteger("time", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the questions.
     *
     * @return The questions associated with the current BasketItemsTwo object
     */
    public List<Map> getQuestions() {
        return getObjects("questions", Map.class);
    }

    /**
     * Returns the duration expressed in minutes.
     *
     * @return The duration associated with the current BasketItemsTwo object
     */
    public Integer getDuration() {
        return getInteger("duration", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the settings.
     *
     * @return The settings associated with the current BasketItemsTwo object
     */
    public BasketItemSettings getSettings() throws IOException{
        if(getRep().getResourcesByRel("settings").size() > 0) {
            return new BasketItemSettings(new HttpServiceResponse((ContentRepresentation) getRep().getResourcesByRel("settings").get(0)));
        }

        return null;
    }

    /**
     * Returns the total price.
     *
     * @return The total price associated with the current BasketItemsTwo object
     */
    public Integer getTotalPrice() {
        return getInteger("total_price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the attachment link.
     *
     * @return The attachment link associated with the current BasketItemsTwo object
     */
    public String getAttachmentLink() {
        return getLink("attachment");
    }

    /**
     * Returns the link for adding an attachment.
     *
     * @return The link for adding an attachment associated with the current BasketItemsTwo object
     */
    public String getAddAtachmentLink() {
        return getLink("add_attachment");
    }

    /**
     * Returns the person link.
     *
     * @return The person link associated with the current BasketItemsTwo object
     */
    public String getPersonLink() {
        return getLink("person");
    }

    /**
     * Returns the service link.
     *
     * @return The service link associated with the current BasketItemsTwo object
     */
    public String getServiceLink() {
        return getLink("service");
    }

    /**
     * Returns the company link.
     *
     * @return The company link associated with the current BasketItemsTwo object
     */
    public String getCompanyLink() {
        return getLink("company");
    }
}
