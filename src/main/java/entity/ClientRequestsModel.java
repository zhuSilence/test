package entity;

/**
 * Created by 00175 on 2015/9/16.
 */
public class ClientRequestsModel {

    private String mac;
    private String ip;
    private String province;
    private String province_name;
    private String city;
    private String city_name;
    private String schedule_id;
    private String order_id;
    private String adspace_id;
    private String media_type;
    private Integer agent_id;
    private String agent_name;
    private Integer customer_id;
    private String company;
    private Integer weekdaynum;
    private String hoursnum;
    private String create_time;
    private String brand;
    private String model;
    private Integer total_traffic;

    public Integer getTotal_traffic() {
        return total_traffic;
    }

    public void setTotal_traffic(Integer total_traffic) {
        this.total_traffic = total_traffic;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAdspace_id() {
        return adspace_id;
    }

    public void setAdspace_id(String adspace_id) {
        this.adspace_id = adspace_id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getWeekdaynum() {
        return weekdaynum;
    }

    public void setWeekdaynum(Integer weekdaynum) {
        this.weekdaynum = weekdaynum;
    }

    public String getHoursnum() {
        return hoursnum;
    }

    public void setHoursnum(String hoursnum) {
        this.hoursnum = hoursnum;
    }

    public Integer getAgent_id() {
        return agent_id;
    }

    public void setAgent_id(Integer agent_id) {
        this.agent_id = agent_id;
    }

    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }
}
