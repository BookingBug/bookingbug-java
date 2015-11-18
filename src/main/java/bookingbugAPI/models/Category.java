package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class Category extends BBRoot{


    public Category(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Category(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public Category() {}


    public String getParentId() {
        return get("parent_id");
    }

    public String getName() {
        return get("name");
    }

    public String getDescription() {
        return get("description");
    }

    public String getCreatedAt() {
        return get("created_at");
    }

    public String getUpdatedAt() {
        return get("updated_at");
    }

    public String getPath() {
        return get("path");
    }

    public String getActive() {
        return get("active");
    }

    public String getCompanyId() {
        return get("company_id");
    }
}
