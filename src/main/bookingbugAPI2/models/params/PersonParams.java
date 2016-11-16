package bookingbugAPI2.models.params;


public class PersonParams {
    public static class Create extends Params<Create> {
        String name;
        String description;
        String email;
        String phonePrefix;
        String phone;
        String icalLink;
        Boolean neverBooked;
        Boolean notify;

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

        public String getPhonePrefix() {
            return phonePrefix;
        }

        public Create setPhonePrefix(String phonePrefix) {
            this.phonePrefix = phonePrefix;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public Create setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getIcalLink() {
            return icalLink;
        }

        public Create setIcalLink(String icalLink) {
            this.icalLink = icalLink;
            return this;
        }

        public Boolean getNeverBooked() {
            return neverBooked;
        }

        public Create setNeverBooked(Boolean neverBooked) {
            this.neverBooked = neverBooked;
            return this;
        }

        public Boolean getNotify() {
            return notify;
        }

        public Create setNotify(Boolean notify) {
            this.notify = notify;
            return this;
        }
    }

    public static class Update extends Params<Update> {
        String name;
        String description;
        String email;
        String phonePrefix;
        String phone;
        String icalLink;
        Boolean neverBooked;
        Boolean notify;

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

        public String getPhonePrefix() {
            return phonePrefix;
        }

        public Update setPhonePrefix(String phonePrefix) {
            this.phonePrefix = phonePrefix;
            return this;
        }

        public String getPhone() {
            return phone;
        }

        public Update setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public String getIcalLink() {
            return icalLink;
        }

        public Update setIcalLink(String icalLink) {
            this.icalLink = icalLink;
            return this;
        }

        public Boolean getNeverBooked() {
            return neverBooked;
        }

        public Update setNeverBooked(Boolean neverBooked) {
            this.neverBooked = neverBooked;
            return this;
        }

        public Boolean getNotify() {
            return notify;
        }

        public Update setNotify(Boolean notify) {
            this.notify = notify;
            return this;
        }
    }
}
