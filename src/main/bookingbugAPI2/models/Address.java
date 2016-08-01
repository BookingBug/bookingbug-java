package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;

public class Address extends BBRoot{

    public Address(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }

    public Address(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    /**
     * Returns the id.
     *
     * @return  the id associated with the current Address object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the first address.
     *
     * @return  the first address associated with the current Address object
     */
    public String getAddress1() {
        return get("address1");
    }

    /**
     * Returns the second address.
     *
     * @return  the second address associated with the current Address object
     */
    public String getAddress2() {
        return get("address2");
    }

    /**
     * Returns the third address.
     *
     * @return  the third address associated with the current Address object
     */
    public String getAddress3() {
        return get("address3");
    }

    /**
     * Returns the fourth address.
     *
     * @return  the fourth address associated with the current Address object
     */
    public String getAddress4() {
        return get("address4");
    }

    /**
     * Returns the fifth address.
     *
     * @return  the fifth address associated with the current Address object
     */
    public String getAddress5() {
        return get("address5");
    }

    /**
     * Returns the post code.
     *
     * @return  the post code associated with the current Address object
     */
    public String getPostcode() {
        return get("postcode");
    }

    /**
     * Returns the country from the address.
     *
     * @return  the country associated with the current Address object
     */
    public String getCountry() {
        return get("country");
    }

    /**
     * Returns the latitude expressed in degrees.
     *
     * @return  the latitude associated with the current Address object
     */
    public Double getLat() {
        return getDouble("lat", DOUBLE_DEFAULT_VALUE);
    }

    /**
     * Returns the longitude expressed in degrees.
     *
     * @return  the longitude associated with the current Address object
     */
    public Double getLong() {
        return getDouble("long", DOUBLE_DEFAULT_VALUE);
    }

    /**
     * Returns the map url.
     *
     * @return  the map url associated with the current Address object
     */
    public String getMapUrl() {
        return get("map_url");
    }

    /**
     * Returns the map marker.
     *
     * @return  the map marker associated with the current Address object
     */
    public String getMapMarker() {
        return get("map_marker");
    }

    /**
     * Returns the phone from address.
     *
     * @return  the phone associated with the current Address object
     */
    public String getPhone() {
        return get("phone");
    }

    /**
     * Returns the home phone from address.
     *
     * @return  the home phone associated with the current Address object
     */
    public String getHomephone() {
        return get("homephone");
    }
}
