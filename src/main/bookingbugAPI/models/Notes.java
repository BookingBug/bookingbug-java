package bookingbugAPI.models;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Notes {
    @JsonProperty("private")
    private String[] privateNotes;
    @JsonProperty("public")
    private String[] publicNotes;

    /**
     * Returns public notes.
     *
     * @return The private notes associated with the Note Object
     */
    public String[] getPublicNotes() {
        return publicNotes;
    }

    /**
     * Returns the private notes.
     *
     * @return The private notes associated with the Note Object
     */
    public String[] getPrivateNotes() {
        return privateNotes;
    }
}
