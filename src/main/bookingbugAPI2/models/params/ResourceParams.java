package bookingbugAPI2.models.params;

/**
 * Created by sebi on 27.06.2016.
 */
public class ResourceParams {

    //TODO: Add all fields
    public static class Create extends Params<Create> {
        String name;
        String description;
        String email;
        boolean disabled;
        boolean never_booked;

        public String getName() {
            return name;
        }

        public Create setName(String name) {
            this.name = name;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Create setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Create setEmail(String email) {
            this.email = email;
            return this;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public Create setDisabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public boolean isNever_booked() {
            return never_booked;
        }

        public Create setNever_booked(boolean never_booked) {
            this.never_booked = never_booked;
            return this;
        }
    }

    //TODO: Add all fields
    public static class Update extends Params<Update> {
        String name;
        String description;
        String email;
        boolean disabled;
        boolean never_booked;

        public String getName() {
            return name;
        }

        public Update setName(String name) {
            this.name = name;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public Update setDescription(String description) {
            this.description = description;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Update setEmail(String email) {
            this.email = email;
            return this;
        }

        public boolean isDisabled() {
            return disabled;
        }

        public Update setDisabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public boolean isNever_booked() {
            return never_booked;
        }

        public Update setNever_booked(boolean never_booked) {
            this.never_booked = never_booked;
            return this;
        }
    }
}
