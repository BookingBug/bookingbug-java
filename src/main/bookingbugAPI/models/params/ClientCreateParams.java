package bookingbugAPI.models.params;

import java.util.Map;

/**
 * Created by sebi on 18.01.2016.
 */
public class ClientCreateParams extends Params {

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

    public ClientCreateParams(){}

    public ClientCreateParams(Map<String, String> params){
        setNotNullStringMap(params);
    }

    /**
     * Get parameters
     * @return the params in a map
     */
    public Map<String, String> getParams() {
        return getNotNullStringMap();
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getAddress5() {
        return address5;
    }

    public void setAddress5(String address5) {
        this.address5 = address5;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMember_type() {
        return member_type;
    }

    public void setMember_type(String member_type) {
        this.member_type = member_type;
    }

    public String getSend_email() {
        return send_email;
    }

    public void setSend_email(String send_email) {
        this.send_email = send_email;
    }

    public String getMember_level_id() {
        return member_level_id;
    }

    public void setMember_level_id(String member_level_id) {
        this.member_level_id = member_level_id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
