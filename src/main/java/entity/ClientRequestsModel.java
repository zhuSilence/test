package entity;

import java.util.Date;

/**
 * Created by 00175 on 2015/9/16.
 */
public class ClientRequestsModel {

    private String mac;
    private String ip;
    private Integer province;
    private Integer city;
    private String schedule_id;
    private String order_id;
    private String adspace_id;
    private String media_type;
    private Integer agent_id;
    private String agent_name;
    private Integer customer_id;
    private String company;
    private Integer weekdaynum;
    private Integer hoursnum;
    private Date create_time;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getWeekdaynum() {
        return weekdaynum;
    }

    public void setWeekdaynum(Integer weekdaynum) {
        this.weekdaynum = weekdaynum;
    }

    public Integer getHoursnum() {
        return hoursnum;
    }

    public void setHoursnum(Integer hoursnum) {
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
