package bookingbugAPI2.api;

import bookingbugAPI2.services.ServiceProvider;

/**
 * Created by sebi on 19.05.2016.
 */
public class API extends AbstractAPI {


    public API(ServiceProvider provider) {
        super(provider);
    }

    public AdminAPI admin() {
        return new AdminAPI(newProvider());
    }
}
