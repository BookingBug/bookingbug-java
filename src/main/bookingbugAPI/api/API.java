package bookingbugAPI.api;

/**
 * Created by sebi on 19.05.2016.
 */
public class API extends AbstractAPI {

    public API(ApiConfig config) {
        super(config);
    }

    public AdminAPI admin() {
        return new AdminAPI(newConfig());
    }

    public static class APIBuilder extends AbstractAPI.ApiConfig<APIBuilder> {

        public API build() {
            return new API(this);
        }
    }
}
