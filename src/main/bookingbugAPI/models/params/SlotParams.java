package bookingbugAPI.models.params;

public class SlotParams {
    public static class Create extends Params<Create> {
        String startTime;
        String endTime;
        String allday;
        String personId;
        String resourceId;

        public String getStartTime() {
            return startTime;
        }

        public Create setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public String getEndTime() {
            return endTime;
        }

        public Create setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public String getAllday() {
            return allday;
        }

        public Create setAllday(String allday) {
            this.allday = allday;
            return this;
        }

        public String getPersonId() {
            return personId;
        }

        public Create setPersonId(String personId) {
            this.personId = personId;
            return this;
        }

        public String getResourceId() {
            return resourceId;
        }

        public Create setResourceId(String resourceId) {
            this.resourceId = resourceId;
            return this;
        }
    }

    public static class Update extends Params<ScheduleParams.Update> {

        String startTime;
        String endTime;
        String allday;
        String personId;
        String resourceId;

        public String getStartTime() {
            return startTime;
        }

        public Update setStartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public String getEndTime() {
            return endTime;
        }

        public Update setEndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public String getAllday() {
            return allday;
        }

        public Update setAllday(String allday) {
            this.allday = allday;
            return this;
        }

        public String getPersonId() {
            return personId;
        }

        public Update setPersonId(String personId) {
            this.personId = personId;
            return this;
        }

        public String getResourceId() {
            return resourceId;
        }

        public Update setResourceId(String resourceId) {
            this.resourceId = resourceId;
            return this;
        }

    }

}
