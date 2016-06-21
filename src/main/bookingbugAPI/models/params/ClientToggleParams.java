package bookingbugAPI.models.params;

/**
 * Created by sebi on 21.06.2016.
 */
public class ClientToggleParams extends Params<ClientToggleParams> {

    String id;
    String email;
    boolean disabled;

    public String getId() {
        return id;
    }

    public ClientToggleParams setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ClientToggleParams setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public ClientToggleParams setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }
}
