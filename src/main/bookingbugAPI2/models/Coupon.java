package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;
import org.joda.time.DateTime;


public class Coupon extends BBRoot {

    public Coupon(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    /**
     * Returns the id of the coupon.
     *
     * @return The id associated with the current Coupon object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the code of the coupon.
     *
     * @return The code associated with the current Coupon object
     */
    public String getCode() {
        return get("code");
    }

    /**
     * Returns the title of the coupon.
     *
     * @return The title associated with the current Coupon object
     */
    public String getTitle() {
        return get("title");
    }

    /**
     * Returns the company's id.
     *
     * @return The company's id associated with the current Coupon object
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the date and time when the validation starts, with {@link DateTime DateTime()} as format.
     *
     * @return The date and time associated with the current Coupon object
     */
    public DateTime getValidFrom() {
        return getDate("valid_from");
    }

    /**
     * Returns how many times the coupon has been used.
     *
     * @return The times used attribute associated with the current Coupon object
     */
    public Integer getTimesUsed() {
        return getInteger("times_used",INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the public attribute.
     *
     * @return The public attribute associated with the current Coupon object
     */
    public Boolean getPublic() {
        return getBoolean("public", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the price of the coupon.
     *
     * @return The price associated with the current Coupon object
     */
    public Double getPrice() {
        return getDouble("price", DOUBLE_DEFAULT_VALUE);
    }

    /**
     * Returns the percentage.
     *
     * @return The percentage associated with the current Coupon object
     */
    public Double getPercentage() {
        return getDouble("percentage", DOUBLE_DEFAULT_VALUE);
    }

    /**
     * Returns the coupon type.
     *
     * @return The coupon type associated with the current Coupon object
     */
    public Integer getCouponType() {
        return getInteger("coupon_type", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the buy attribute.
     *
     * @return The buy attribute associated with the current Coupon object
     */
    public Integer getBuy() {
        return getInteger("buy", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns true if the coupon is free, false otherwise.
     *
     * @return The free attribute associated with the current Coupon object
     */
    public Integer getFree() {
        return getInteger("free", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the apply to questions attribute.
     *
     * @return The apply to questions attribute associated with the current Coupon object
     */
    public Boolean getApplyToQuestions() {
        return getBoolean("apply_to_questions", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the maximum coupons per person.
     *
     * @return The maximum coupons per person associated with the current Coupon object
     */
    public Integer getMaxPerPerson() {
        return getInteger("max_per_person", INTEGER_DEFAULT_VALUE);
    }
}
