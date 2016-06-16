package bookingbugAPI.models;

import bookingbugAPI.services.PlainHttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;


public class Member extends BBRoot {

    public Member(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public BBRoot getSchema() throws IOException{
        String link = getRep().getLinkByRel("edit_member").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        HttpServiceResponse schema_rep = PlainHttpService.api_GET(url, auth_token);
        return new BBRoot(schema_rep);
    }

    /**
     * Returns the member id.
     *
     * @return the member id associated with the current Member object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the name.
     *
     * @return the name associated with the current Member object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the member first name.
     *
     * @return the member first name associated with the current Member object
     */
    public String getFirstName() {
        return get("first_name");
    }

    /**
     * Returns the member last name.
     *
     * @return the member last name associated with the current Member object
     */
    public String getLastName() {
        return get("last_name");
    }

    /**
     * Returns the client type.
     *
     * @return the client type associated with the current Member object
     */
    public String getClientType() {
        return get("client_type");
    }

    /**
     * Returns the email.
     *
     * @return the email associated with the current Member object
     */
    public String getEmail() {
        return get("email");
    }

    /**
     * Returns the first address.
     *
     * @return the first address associated with the current Member object
     */
    public String getAddress1() {
        return get("address1");
    }

    /**
     * Returns the second address.
     *
     * @return the second address associated with the current Member object
     */
    public String getAddress2() {
        return get("address2");
    }

    /**
     * Returns the third address.
     *
     * @return the third address associated with the current Member object
     */
    public String getAddress3() {
        return get("address3");
    }

    /**
     * Returns the forth address.
     *
     * @return the forth address associated with the current Member object
     */
    public String getAddress4() {
        return get("address4");
    }

    /**
     * Returns the postal code.
     *
     * @return the postal code associated with the current Member object
     */
    public String getPostCode() {
        return get("postcode");
    }

    /**
     * Returns the country.
     *
     * @return the country associated with the current Member object
     */
    public String getCountry() {
        return get("country");
    }

    /**
     * Returns the phone with the prefix found in {@link Member#getPhonePrefix()}.
     *
     * @return the phone associated with the current Member object
     */
    public String getPhone() {
        return get("phone");
    }

    /**
     * Returns the phone prefix.
     *
     * @return the phone prefix associated with the current Member object
     */
    public String getPhonePrefix() {
        return get("phone_prefix");
    }

    /**
     * Returns the mobile with the prefix found in {@link Member#getMobilePrefix()}.
     *
     * @return the mobile associated with the current Member object
     */
    public String getMobile() {
        return get("mobile");
    }

    /**
     * Returns the mobile prefix.
     *
     * @return the mobile prefix associated with the current Member object
     */
    public String getMobilePrefix() {
        return get("mobile_prefix");
    }

    /**
     * Returns the authentication token.
     *
     * @return the authentication token associated with the current Member object
     */
    public String getAuthToken() {
        return get("auth_token");
    }

    /**
     * Returns the path to the API.
     *
     * @return the path associated with the current Member object
     */
    public String getPath() {
        return get("path");
    }

    /**
     * Returns the company id.
     *
     * @return the company id associated with the current Member object
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns true if member has active wallet, false otherwise.
     *
     * @return the active wallet attribute associated with the current Member object
     */
    public Boolean getHasActiveWallet() {
        return getBoolean("has_active_wallet", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns true if the member has wallet, false otherwise.
     *
     * @return the wallet attribute associated with the current Member object
     */
    public Boolean getHasWallet() {
        return getBoolean("has_wallet", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the bookings link.
     *
     * @return the bookings link associated with the current Member object
     */
    public String getBookingsLink() {
        return getLink("bookings");
    }

    /**
     * Returns the pre paid bookings link.
     *
     * @return the pre paid bookings link associated with the current Member object
     */
    public String getPrePaidBookingslink() {
        return getLink("pre_paid_bookings");
    }

    /**
     * Returns the purchase totals link.
     *
     * @return the purchase totals link associated with the current Member object
     */
    public String getPurchaseTotalsLink() {
        return getLink("purchase_totals");
    }

    /**
     * Returns the edit member link.
     *
     * @return the edit member link associated with the current Member object
     */
    public String getEditMemberLink() {
        return getLink("edit_member");
    }

    /**
     * Returns the company link.
     *
     * @return the company link associated with the current Member object
     */
    public String getCompanyLink() {
        return getLink("company");
    }

    /**
     * Returns the update password link.
     *
     * @return the update password link associated with the current Member object
     */
    public String getUpdatePasswordLink() {
        return getLink("update_password");
    }

    /**
     * Returns the send welcome email link.
     *
     * @return the send welcome email link associated with the current Member object
     */
    public String getSendWelcomeEmailLink() {
        return getLink("send_welcome_email");
    }

}
