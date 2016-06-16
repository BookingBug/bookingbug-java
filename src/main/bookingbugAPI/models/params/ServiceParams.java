package bookingbugAPI.models.params;

/**
 * Created by sebi on 16.06.2016.
 */
public class ServiceParams {

    public static class ServiceCreateParams extends Params<ServiceCreateParams> {

        String name;
        String reference;
        Integer duration;
        Integer spaces;

        public String getName() {
            return name;
        }

        public ServiceCreateParams setName(String name) {
            this.name = name;
            return this;
        }

        public String getReference() {
            return reference;
        }

        public ServiceCreateParams setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Integer getDuration() {
            return duration;
        }

        public ServiceCreateParams setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }

        public Integer getSpaces() {
            return spaces;
        }

        public ServiceCreateParams setSpaces(Integer spaces) {
            this.spaces = spaces;
            return this;
        }
    }

    public static class ServiceUpdateParams extends Params<ServiceUpdateParams> {

        String name;
        String reference;
        Integer duration;

        public String getName() {
            return name;
        }

        public ServiceUpdateParams setName(String name) {
            this.name = name;
            return this;
        }

        public String getReference() {
            return reference;
        }

        public ServiceUpdateParams setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public Integer getDuration() {
            return duration;
        }

        public ServiceUpdateParams setDuration(Integer duration) {
            this.duration = duration;
            return this;
        }
    }
}
