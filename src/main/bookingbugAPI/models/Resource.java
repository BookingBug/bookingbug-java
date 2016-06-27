package bookingbugAPI.models;

import com.j256.ormlite.stmt.query.In;
import helpers.HttpServiceResponse;


public class Resource extends BBRoot {


    public Resource(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Resource(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Resource() {
    }

    /**
     * Returns the resource id.
     *
     * @return the resource id associated with the current Resource object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the resource name.
     *
     * @return the resource name associated with the current Resource object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the resource type.
     *
     * @return the resource type associated with the current Resource object
     */
    public String getType() {
        return get("type");
    }

    /**
     * Returns true if the resource is deleted, false otherwise.
     *
     * @return the deleted attribute associated with the current Resource object
     */
    public Boolean getDeleted() {
        return getBoolean("deleted", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns true if the resource is disabled, false otherwise.
     *
     * @return the disabled attribute associated with the current Resource object
     */
    public Boolean getDisabled() {
        return getBoolean("disabled", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the company id.
     *
     * @return the company id associated with the current Resource object
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the email.
     *
     * @return the email associated with the current Resource object
     */
    public String getEmail() {
        return get("email");
    }

    /**
     * Returns the order.
     *
     * @return the order associated with the current Resource object
     */
    public Integer getOrder() {
        return getInteger("order", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the edit link
     * @return the link to edit this service
     */
    public String getEditLink() {
        return getLink("edit");
    }

    /**
     * Returns the items link.
     *
     * @return the items link associated with the current Resource object
     */
    public String getItemsLink() {
        return getLink("items");
    }

    /**
     * Returns the address link.
     *
     * @return the address link associated with the current Resource object
     */
    public String getAddressLink() {
        return getLink("address");
    }

}
