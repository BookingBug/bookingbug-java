package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class Basket extends BBRoot{

    public Basket(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public String getCompanyId() {
        return get("company_id");
    }

    /**
     * Returns the total price of the basket.
     *
     * @return The total price associated with the current Basket object
     */
    public Integer getTotalPrice() {
        return getInteger("total_price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the total due price of the basket.
     *
     * @return The total due price associated with the current Basket object
     */
    public Integer getTotalDuePrice() {
        return getInteger("total_due_price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the items from the basket.
     *
     * @return {@link BBCollection<BasketItem>} The items associated with the current Basket object
     */
    public BBCollection<BasketItem> getItems() {
        return new BBCollection<>(new HttpServiceResponse(getResource("items")), auth_token, BasketItem.class);
    }

    /**
     * Returns the checkout link.
     *
     * @return The checkout link associated with the current Basket object
     */
    public String getCheckoutLink() {
        return getLink("checkout");
    }

    /**
     * Returns the items link.
     *
     * @return The items link associated with the current Basket object
     */
    public String getItemsLink() {
        return getLink("items");
    }

    /**
     * Returns the link for adding an item.
     *
     * @return The link for adding an item associated with the current Basket object
     */
    public String getAddItemLink() {
        return getLink("add_item");
    }

    /**
     * Returns the deal link.
     *
     * @return The deal link associated with the current Basket object
     */
    public String getDealLink() {
        return getLink("deal");
    }
}
