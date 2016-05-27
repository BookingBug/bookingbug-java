package bookingbugAPI.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import helpers.HttpServiceResponse;

import java.util.List;
import java.util.Map;

public class Client extends BBRoot {

    public Client(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public Client(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Client() {
    }

    /**
     * Returns the client's first name.
     *
     * @return The first name associated with the current Client object.
     */
    public String getFirstName() {
        return get("first_name");
    }

    /**
     * Returns the client's last name.
     *
     * @return The last name associated with the current Client object.
     */
    public String getLastName() {
        return get("last_name");
    }

    /**
     * Returns the client's email.
     *
     * @return The last name associated with the current Client object.
     */
    public String getEmail() {
            return get("email");
    }

    /**
     * Returns the first address, if exists.
     *
     * @return The first address associated with the current Client object.
     */
    public String getAddress1() {
        return get("address1");
    }

    /**
     * Returns the second address, if exists.
     *
     * @return The second address associated with the current Client object.
     */
    public String getAddress2() {
        return get("address2");
    }

    /**
     * Returns the third address, if exists.
     *
     * @return The third address associated with the current Client object.
     */
    public String getAddress3() {
        return get("address3");
    }

    /**
     * Returns the fourth address, if exists.
     *
     * @return The fourth address associated with the current Client object.
     */
    public String getAddress4() {
        return get("address4");
    }

    /**
     * Returns the fifth address, if exists.
     *
     * @return The fifth address associated with the current Client object.
     */
    public String getAddress5() {
        return get("address5");
    }

    /**
     * Returns the post code.
     *
     * @return The post code associated with the current Client object.
     */
    public String getPostcode() {
        return get("postcode");
    }

    /**
     * Returns the country.
     *
     * @return The country associated with the current Client object.
     */
    public String getCountry() {
        return get("country");
    }

    /**
     * Returns the client's phone.
     *
     * @return The phone associated with the current Client object.
     */
    public String getPhone() {
        return get("phone");
    }

    /**
     * Returns the client's mobile.
     *
     * @return The mobile associated with the current Client object.
     */
    public String getMobile() {
        return get("mobile");
    }


    /**
     * Returns the client's id.
     *
     * @return The id associated with the current Client object.
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the client's member type.
     *
     * @return The member type associated with the current Client object.
     */
    public Integer getMemberType() {
        return getInteger("member_type", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the reference.
     *
     * @return The reference associated with the current Client object.
     */
    public String getReference() {
        return get("reference");
    }

    /**
     * Returns the files.
     *
     * @return The files associated with the current Client object.
     */
    public List<String> getFiles() {
        return getArray("files");
    }

    /**
     * Returns the answers.
     *
     * @return The answers associated with the current Client object.
     */
    public List<Answer> getAnswers() {
        return getObjects("answers", Answer.class);
    }

    /**
     * Returns the client if it's deleted attribute.
     *
     * @return True if the client is deleted, false otherwise.
     */
    public Boolean getDeleted() {
        return getBoolean("deleted", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the client's phone prefix.
     *
     * @return The phone prefix associated with the current Client object.
     */
    public String getPhonePrefix() {
        return get("phone_prefix");
    }

    /**
     * Returns the client's mobile prefix.
     *
     * @return The mobile prefix associated with the current Client object.
     */
    public String getMobilePrefix() {
        return get("mobile_prefix");
    }

    /**
     * Returns the client's .
     *
     * @return The  associated with the current Client object.
     */
    public Map getQ() {
        return getObject("q", Map.class);
    }

    /**
     * Returns the bookings link.
     *
     * @return The bookings link associated with the current Client object.
     */
    public String getBookingsLink() {
        return getLink("bookings");
    }

    /**
     * Returns the pre paid bookings link.
     *
     * @return The pre paid bookings link associated with the current Client object.
     */
    public String getPrePaidBookingsLink() {
        return getLink("pre_paid_bookings");
    }

    /**
     * Returns the questions link.
     *
     * @return The questions link associated with the current Client object.
     */
    public String getQuestionsLink() {
        return getLink("questions");
    }

    /**
     * Returns the edit link.
     *
     * @return The edit link associated with the current Client object.
     */
    public String getEditLink() {
        return getLink("edit");
    }
}
