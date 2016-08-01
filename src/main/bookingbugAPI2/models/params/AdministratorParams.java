package bookingbugAPI2.models.params;

public class AdministratorParams {
    public static class Create extends Params<Create> {

        String name;
        String role;
        Integer serviceId;
        Integer resourceId;
        Integer personId;

        public String getName() {
            return name;
        }

        public Create setName(String name) {
            this.name = name;
            return this;
        }

        public String getRole() {
            return role;
        }

        public Create setRole(String role) {
            this.role = role;
            return this;
        }

        public Integer getServiceId() {
            return serviceId;
        }

        public Create setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Integer getResourceId() {
            return resourceId;
        }

        public Create setResourceId(Integer resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Integer getPersonId() {
            return personId;
        }

        public Create setPersonId(Integer personId) {
            this.personId = personId;
            return this;
        }
    }

    public static class Update extends Params<Update> {

        String name;
        String role;
        Integer serviceId;
        Integer resourceId;
        Integer personId;

        public String getName() {
            return name;
        }

        public Update setName(String name) {
            this.name = name;
            return this;
        }

        public String getRole() {
            return role;
        }

        public Update setRole(String role) {
            this.role = role;
            return this;
        }

        public Integer getServiceId() {
            return serviceId;
        }

        public Update setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Integer getResourceId() {
            return resourceId;
        }

        public Update setResourceId(Integer resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Integer getPersonId() {
            return personId;
        }

        public Update setPersonId(Integer personId) {
            this.personId = personId;
            return this;
        }
    }
}

