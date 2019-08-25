package com.aottec.arkot.gps.Model;

import java.util.List;

public class DrawerObjectResponseModel {

    /**
     * imei : 9170830930
     * protocol : lkgps
     * net_protocol : tcp
     * ip : 106.208.32.16
     * port : 11100
     * active : true
     * object_expire : true
     * object_expire_dt : 2020-07-01
     * dt_server : 2019-07-08 15:36:21
     * dt_tracker : 2019-07-08 13:59:03
     * lat : 11.069647
     * lng : 77.004087
     * altitude : 0
     * angle : 200
     * speed : 0
     * params : {"pwrcut":"0","shock":"0","acc":"0","arm":"0"}
     * loc_valid : 0
     * dt_last_stop : 2019-07-08 13:53:54
     * dt_last_idle : 0000-00-00 00:00:00
     * dt_last_move : 2019-07-08 13:54:16
     * name : TN58AD8192
     * device : SinoTrack
     * sim_number : 9600997103
     * model :
     * vin :
     * plate_number : TN58AD8192
     * odometer : 89912.8289040035
     * engine_hours : 1118155
     * custom_fields : []
     */

    private String imei;
    private String protocol;
    private String net_protocol;
    private String ip;
    private String port;
    private String active;
    private String object_expire;
    private String object_expire_dt;
    private String dt_server;
    private String dt_tracker;
    private String lat;
    private String lng;
    private String altitude;
    private String angle;
    private String speed;
    private ParamsBean params;
    private String loc_valid;
    private String dt_last_stop;
    private String dt_last_idle;
    private String dt_last_move;
    private String name;
    private String device;
    private String sim_number;
    private String model;
    private String vin;
    private String plate_number;
    private String odometer;
    private String engine_hours;
    private List<?> custom_fields;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getNet_protocol() {
        return net_protocol;
    }

    public void setNet_protocol(String net_protocol) {
        this.net_protocol = net_protocol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getObject_expire() {
        return object_expire;
    }

    public void setObject_expire(String object_expire) {
        this.object_expire = object_expire;
    }

    public String getObject_expire_dt() {
        return object_expire_dt;
    }

    public void setObject_expire_dt(String object_expire_dt) {
        this.object_expire_dt = object_expire_dt;
    }

    public String getDt_server() {
        return dt_server;
    }

    public void setDt_server(String dt_server) {
        this.dt_server = dt_server;
    }

    public String getDt_tracker() {
        return dt_tracker;
    }

    public void setDt_tracker(String dt_tracker) {
        this.dt_tracker = dt_tracker;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public String getLoc_valid() {
        return loc_valid;
    }

    public void setLoc_valid(String loc_valid) {
        this.loc_valid = loc_valid;
    }

    public String getDt_last_stop() {
        return dt_last_stop;
    }

    public void setDt_last_stop(String dt_last_stop) {
        this.dt_last_stop = dt_last_stop;
    }

    public String getDt_last_idle() {
        return dt_last_idle;
    }

    public void setDt_last_idle(String dt_last_idle) {
        this.dt_last_idle = dt_last_idle;
    }

    public String getDt_last_move() {
        return dt_last_move;
    }

    public void setDt_last_move(String dt_last_move) {
        this.dt_last_move = dt_last_move;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSim_number() {
        return sim_number;
    }

    public void setSim_number(String sim_number) {
        this.sim_number = sim_number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPlate_number() {
        return plate_number;
    }

    public void setPlate_number(String plate_number) {
        this.plate_number = plate_number;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getEngine_hours() {
        return engine_hours;
    }

    public void setEngine_hours(String engine_hours) {
        this.engine_hours = engine_hours;
    }

    public List<?> getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(List<?> custom_fields) {
        this.custom_fields = custom_fields;
    }

    public static class ParamsBean {
        /**
         * pwrcut : 0
         * shock : 0
         * acc : 0
         * arm : 0
         */

        private String pwrcut;
        private String shock;
        private String acc;
        private String arm;

        public String getPwrcut() {
            return pwrcut;
        }

        public void setPwrcut(String pwrcut) {
            this.pwrcut = pwrcut;
        }

        public String getShock() {
            return shock;
        }

        public void setShock(String shock) {
            this.shock = shock;
        }

        public String getAcc() {
            return acc;
        }

        public void setAcc(String acc) {
            this.acc = acc;
        }

        public String getArm() {
            return arm;
        }

        public void setArm(String arm) {
            this.arm = arm;
        }
    }
}
