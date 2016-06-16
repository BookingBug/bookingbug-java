package bookingbugAPI.api;

import bookingbugAPI.services.AbstractHttpService;
import bookingbugAPI.services.OkHttpService;

/**
 * Created by sebi on 19.05.2016.
 */
public class API extends AbstractAPI {

    public API(AbstractHttpService httpService, ApiConfig config) {
        super(httpService, config);
    }

    public AdminAPI admin() {
        return new AdminAPI(httpService, newConfig());
    }

    public static class APIBuilder extends AbstractAPI.ApiConfig<APIBuilder> {

        AbstractHttpService httpService;

        public APIBuilder withHttpService(AbstractHttpService httpService) {
            this.httpService = httpService;
            return this;
        }

        public API build() {
            //Default is OkHttpService
            if(httpService == null)
                httpService = new OkHttpService(this);
            return new API(httpService, this);
        }
    }
}
