package com.aottec.arkotgps.Model;

public class LoginResponseModel {
    /**
     * status : Success
     * api_key : 39E0D298AD3A34DDFB9760C1B6942268
     */

    private String status;
    private String api_key;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApi_key() {
        return api_key;
    }

    public void setApi_key(String api_key) {
        this.api_key = api_key;
    }
}
