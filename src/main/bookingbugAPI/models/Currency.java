package bookingbugAPI.models;

/**
 * Created by sebi on 31.03.2016.
 */
public enum  Currency {
    GBP("GBP"),
    EUR("EUR");

    private final String apiValue;

    private Currency(String apiValue) {
        this.apiValue = apiValue;
    }

    public String symbol() {
        switch (this) {
            case GBP: return "\u00a3";
            case EUR: return "\u20ac";
            default: return "";
        }
    }

    public static Currency fromString(String str) {
        if(str != null) {
            for(Currency currency : Currency.values()) {
                if(str.equalsIgnoreCase(currency.apiValue))
                    return currency;
            }
        }
        return null;
    }
}
