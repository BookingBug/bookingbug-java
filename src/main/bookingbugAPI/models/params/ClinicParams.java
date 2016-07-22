package bookingbugAPI.models.params;

import org.joda.time.DateTime;


public class ClinicParams {
    public static class Create extends Params<Create> {
        String name;
        DateTime startTime;
        DateTime endTime;

        public String getName() {
            return name;
        }

        public Create setName(String name) {
            this.name = name;
            return this;
        }

        public DateTime getStartTime() {
            return startTime;
        }

        public Create setStartTime(DateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public DateTime getEndTime() {
            return endTime;
        }

        public Create setEndTime(DateTime endTime) {
            this.endTime = endTime;
            return this;
        }
    }

    public static class Update extends Params<PersonParams.Update> {

        String name;
        DateTime startTime;
        DateTime endTime;

        public String getName() {
            return name;
        }

        public Update setName(String name) {
            this.name = name;
            return this;
        }

        public DateTime getStartTime() {
            return startTime;
        }

        public Update setStartTime(DateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public DateTime getEndTime() {
            return endTime;
        }

        public Update setEndTime(DateTime endTime) {
            this.endTime = endTime;
            return this;
        }

    }
}
