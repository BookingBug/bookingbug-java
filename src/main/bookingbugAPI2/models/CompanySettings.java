package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;

/**
 * Created by sebi on 31.03.2016.
 */
public class CompanySettings extends BBRoot {

    public CompanySettings(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public CompanySettings(HttpServiceResponse response) {
        super(response);
    }

    /**
     * Returns true if it has coupons, false otherwise.
     *
     * @return the has coupons attribute associated with the current company settings.
     */
    public boolean hasCoupons() {
        return getBoolean("has_coupons", false);
    }

    /**
     * Returns true if use shopping basket, false otherwise.
     *
     * @return the use shopping basket attribute associated with the current company settings.
     */
    public boolean useShoppingBasket() {
        return getBoolean("use_shopping_basket", false);
    }

    /**
     * Returns true if it has deals, false otherwise.
     *
     * @return the has deals attribute associated with the current company settings.
     */
    public boolean hasDeals() {
        return getBoolean("has_deals", false);
    }

    /**
     * Returns true if it has vouchers, false otherwise.
     *
     * @return the has vouchers associated with the current company settings.
     */
    public boolean hasVouchers() {
        return getBoolean("has_vouchers", false);
    }

    /**
     * Returns true if it has products, false otherwise.
     *
     * @return the has products attribute associated with the current company settings.
     */
    public boolean hasProducts() {
        return getBoolean("has_products", false);
    }

    /**
     * Returns true if it has services, false otherwise.
     *
     * @return the a associated with the current company settings.
     */
    public boolean hasServices() {
        return getBoolean("has_services", false);
    }

    /**
     * Returns true if it has events, false otherwise.
     *
     * @return the has events attribute associated with the current company settings.
     */
    public boolean hasEvents() {
        return getBoolean("has_events", false);
    }

    /**
     * Returns true if it has classes, false otherwise.
     *
     * @return the has classes attribute associated with the current company settings.
     */
    public boolean hasClasses() {
        return getBoolean("has_classes", false);
    }

    /**
     * Returns true if it has waitlists, false otherwise.
     *
     * @return the has waitlists attribute associated with the current company settings.
     */
    public boolean hasWaitlists() {
        return getBoolean("has_waitlists", false);
    }

    /**
     * Returns true if login is required.
     *
     * @return the requires login attribute associated with the current company settings.
     */
    public boolean requiresLogin() {
        return getBoolean("requires_login", false);
    }

    /**
     * Returns true if it has wallets, false otherwise.
     *
     * @return the has wallets attribute associated with the current company settings.
     */
    public boolean hasWallets() {
        return getBoolean("has_wallets", false);
    }

    /**
     * Returns the payment tax.
     *
     * @return the payment tax associated with the current company settings.
     */
    public int getPaymentTax() {
        return getInteger("payment_tax", 0);
    }

    /**
     * Returns the currency.
     *
     * @return the currency associated with the current company settings.
     */
    public Currency getCurrency() {
        return Currency.fromString(get("currency"));
    }
}
