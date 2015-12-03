package bookingbugAPI.api;

/**
 * Created by sebi on 5/30/15.
 */
public abstract class AuthedAPI {
    String auth_token;

    public AuthedAPI(String token){
        auth_token = token;
    }
}
