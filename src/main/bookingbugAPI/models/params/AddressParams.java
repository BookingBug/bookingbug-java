package bookingbugAPI.models.params;


public class AddressParams {
    public static class Create extends Params<Create> {

        String address1;
        String address2;
        String address3;
        String address4;
        String address5;
        String postcode;
        String country;

        public String getAddress1() {
            return address1;
        }

        public Create setAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public String getAddress2() {
            return address2;
        }

        public Create setAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public String getAddress3() {
            return address3;
        }

        public Create setAddress3(String address3) {
            this.address3 = address3;
            return this;
        }

        public String getAddress4() {
            return address4;
        }

        public Create setAddress4(String address4) {
            this.address4 = address4;
            return this;
        }

        public String getAddress5() {
            return address5;
        }

        public Create setAddress5(String address5) {
            this.address5 = address5;
            return this;
        }

        public String getPostcode() {
            return postcode;
        }

        public Create setPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Create setCountry(String country) {
            this.country = country;
            return this;
        }
    }

    public static class Update extends Params<Update> {

        String address1;
        String address2;
        String address3;
        String address4;
        String address5;
        String postcode;
        String country;

        public String getAddress1() {
            return address1;
        }

        public Update setAddress1(String address1) {
            this.address1 = address1;
            return this;
        }

        public String getAddress2() {
            return address2;
        }

        public Update setAddress2(String address2) {
            this.address2 = address2;
            return this;
        }

        public String getAddress3() {
            return address3;
        }

        public Update setAddress3(String address3) {
            this.address3 = address3;
            return this;
        }

        public String getAddress4() {
            return address4;
        }

        public Update setAddress4(String address4) {
            this.address4 = address4;
            return this;
        }

        public String getAddress5() {
            return address5;
        }

        public Update setAddress5(String address5) {
            this.address5 = address5;
            return this;
        }

        public String getPostcode() {
            return postcode;
        }

        public Update setPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Update setCountry(String country) {
            this.country = country;
            return this;
        }
    }
}
