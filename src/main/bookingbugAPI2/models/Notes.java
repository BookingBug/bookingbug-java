package bookingbugAPI2.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Notes extends BBRoot {
    @JsonProperty("private")
    private List<Note> privateNotes;
    @JsonProperty("public")
    private List<Note> publicNotes;

    /**
     * Returns public notes.
     *
     * @return The private notes associated with the Note Object
     */
    public List<Note> getPublicNotes() {
        return publicNotes;
    }

    /**
     * Returns the private notes.
     *
     * @return The private notes associated with the Note Object
     */
    public List<Note> getPrivateNotes() {
        return privateNotes;
    }
}
