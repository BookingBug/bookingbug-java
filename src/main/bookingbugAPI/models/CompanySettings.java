package bookingbugAPI.models;

import helpers.HttpServiceResponse;

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

    public boolean has_coupons() {
        return getBoolean("has_coupons", false);
    }

    public boolean has_deals() {
        return getBoolean("has_deals", false);
    }

    public boolean has_products() {
        return getBoolean("has_products", false);
    }

    public boolean has_events() {
        return getBoolean("has_events", false);
    }

    public boolean has_classes() {
        return getBoolean("has_classes", false);
    }

    public boolean requires_login() {
        return getBoolean("requires_login", false);
    }

    public boolean has_wallets() {
        return getBoolean("has_wallets", false);
    }

    public int getPaymentTax() {
        return getInteger("payment_tax", 0);
    }



    public Currency getCurrency() {
        return Currency.fromString(get("currency"));
    }
}
