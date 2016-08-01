package bookingbugAPI2.models.params;


public class EventChainParams {


    public static class Create extends Params<Create> {
        String name;
        String description;
        String longDescription;
        Integer spaces;
        Integer resourceId;
        Integer eventGroupId;
        Integer duration;
        String datetime;
        Integer price;
        String ticketType;
        Integer addressId;
        String reference;

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

        public String getLongDescription() {
            return longDescription;
        }

        public Create setLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Integer getSpaces() {
            return spaces;
        }

        public Create setSpaces(Integer spaces) {
            this.spaces = spaces;
            return this;
        }

        public Integer getResourceId() {
            return resourceId;
        }

        public Create setResourceId(Integer resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Integer getEventGroupId() {
            return eventGroupId;
        }

        public Create setEventGroupId(Integer eventGroupId) {
            this.eventGroupId = eventGroupId;
            return this;
        }

        public Integer getDuration() {
            return duration;
        }

        public Create setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public String getDatetime() {
            return datetime;
        }

        public Create setDatetime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Integer getPrice() {
            return price;
        }

        public Create setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public String getTicketType() {
            return ticketType;
        }

        public Create setTicketType(String ticketType) {
            this.ticketType = ticketType;
            return this;
        }

        public Integer getAddressId() {
            return addressId;
        }

        public Create setAddressId(Integer addressId) {
            this.addressId = addressId;
            return this;
        }

        public String getReference() {
            return reference;
        }

        public Create setReference(String reference) {
            this.reference = reference;
            return this;
        }
    }

    public static class Update extends Params<Update> {

        String name;
        String description;
        String longDescription;
        Integer spaces;
        Integer resourceId;
        Integer personId;
        Integer eventGroupId;
        Integer duration;
        String datetime;
        Integer price;
        String ticketType;
        Integer addressId;
        String reference;

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

        public String getLongDescription() {
            return longDescription;
        }

        public Update setLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Integer getSpaces() {
            return spaces;
        }

        public Update setSpaces(Integer spaces) {
            this.spaces = spaces;
            return this;
        }

        public Integer getResourceId() {
            return resourceId;
        }

        public Integer getPersonId() {
            return personId;
        }

        public void setPersonId(Integer personId) {
            this.personId = personId;
        }

        public Update setResourceId(Integer resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Integer getEventGroupId() {
            return eventGroupId;
        }

        public Update setEventGroupId(Integer eventGroupId) {
            this.eventGroupId = eventGroupId;
            return this;
        }

        public Integer getDuration() {
            return duration;
        }

        public Update setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public String getDatetime() {
            return datetime;
        }

        public Update setDatetime(String datetime) {
            this.datetime = datetime;
            return this;
        }

        public Integer getPrice() {
            return price;
        }

        public Update setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public String getTicketType() {
            return ticketType;
        }

        public Update setTicketType(String ticketType) {
            this.ticketType = ticketType;
            return this;
        }

        public Integer getAddressId() {
            return addressId;
        }

        public Update setAddressId(Integer addressId) {
            this.addressId = addressId;
            return this;
        }

        public String getReference() {
            return reference;
        }

        public Update setReference(String reference) {
            this.reference = reference;
            return this;
        }
    }
}
