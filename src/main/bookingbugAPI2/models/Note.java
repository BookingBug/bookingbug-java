package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class Note extends BBRoot {

    public Note(){}

    public Note(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    /**
     * Returns the note id.
     *
     * @return The note id associated with the Note Object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the note text.
     *
     * @return The note text associated with the Note Object
     */
    public String getNote() {
        return get("note");
    }
}
