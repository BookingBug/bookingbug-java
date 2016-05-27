package bookingbugAPI.models;



public class Option extends BBRoot{

    /**
     * Returns the option name.
     *
     * @return The option name associated with the current Option object.
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the price.
     *
     * @return The price associated with the current Option object.
     */
    public Integer getPrice() {
        return getInteger("price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the is default attribute.
     *
     * @return The is default attribute associated with the current Option object.
     */
    public Boolean getIs_default() {
        return getBoolean("is_default", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the option id.
     *
     * @return The option id associated with the current Option object.
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }
}
