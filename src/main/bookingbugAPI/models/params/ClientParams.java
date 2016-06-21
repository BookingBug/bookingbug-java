package bookingbugAPI.models.params;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by sebi on 21.06.2016.
 */
public class ClientParams {

    public static class Create extends Params<Create> {
        String first_name;
        String last_name;
        String email;
        String mobile;
        String address1;
        String address2;
        String address3;
        String address4;
        String address5;
        String postcode;
        String reference;
        String member_type;
        String country;
        String send_email;
        String member_level_id;

        public String getFirst_name() {
            return first_name;
        }

        public Create setFirst_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public String getLast_name() {
            return last_name;
        }

        public Create setLast_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Create setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getMobile() {
            return mobile;
        }

        public Create setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

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

        public String getReference() {
            return reference;
        }

        public Create setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public String getMember_type() {
            return member_type;
        }

        public Create setMember_type(String member_type) {
            this.member_type = member_type;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Create setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getSend_email() {
            return send_email;
        }

        public Create setSend_email(String send_email) {
            this.send_email = send_email;
            return this;
        }

        public String getMember_level_id() {
            return member_level_id;
        }

        public Create setMember_level_id(String member_level_id) {
            this.member_level_id = member_level_id;
            return this;
        }
    }

    public static class Update extends Params<Update> {
        String first_name;
        String last_name;
        String email;
        String mobile;
        String address1;
        String address2;
        String address3;
        String address4;
        String address5;
        String postcode;
        String reference;
        String join_date;
        String country;
        String member_level_id;

        public String getFirst_name() {
            return first_name;
        }

        public Update setFirst_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public String getLast_name() {
            return last_name;
        }

        public Update setLast_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public Update setEmail(String email) {
            this.email = email;
            return this;
        }

        public String getMobile() {
            return mobile;
        }

        public Update setMobile(String mobile) {
            this.mobile = mobile;
            return this;
        }

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

        public String getReference() {
            return reference;
        }

        public Update setReference(String reference) {
            this.reference = reference;
            return this;
        }

        public DateTime getJoin_date() {
            return new DateTime(join_date);
        }

        public Update setJoin_date(DateTime join_date) {
            this.join_date = ISODateTimeFormat.dateTime().print(join_date);
            return this;
        }

        public String getCountry() {
            return country;
        }

        public Update setCountry(String country) {
            this.country = country;
            return this;
        }

        public String getMember_level_id() {
            return member_level_id;
        }

        public Update setMember_level_id(String member_level_id) {
            this.member_level_id = member_level_id;
            return this;
        }
    }
}
