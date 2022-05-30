/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sociopath;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 *
 * @author Lidros
 */
public class Student implements Comparable<Student>{
    private String name;
    private Double divingRate;
    private Integer rep;    
    private Integer lunchTime;
    private Integer lunchPeriod;
    private java.util.ArrayList friendsList;
    private Integer endLunch;
    
    public Student(String name, Double divingRate, Integer rep, Integer lunchTime, Integer lunchPeriod) {
        this.name = name;
        this.divingRate = divingRate;
        this.rep = rep; //tak pasti guna lagi tak
        this.lunchTime = lunchTime;
        this.lunchPeriod = lunchPeriod;
        if ((lunchTime + lunchPeriod) % 100 >= 60) {
            endLunch = lunchTime - 60;
        }
        else
            endLunch = lunchTime + lunchPeriod;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public Integer getLunchTime() {
        return lunchTime;
    }

    public Integer getLunchPeriod() {
        return lunchPeriod;
    }

    public Double getDivingRate() {
        return divingRate;
    }

    public Integer getEndLunch() {
        return endLunch;
    }
    
    public void setFriendsList(ArrayList friendsList) {
        this.friendsList = friendsList;
    }
    
    /**
     * check whether lunchtime of this student clash or not with toCheck student
     * @param Student toCheck
     * @return clash lunch time or not
     */
    public boolean clashLunch(Student toCheck){
        if (endLunch < toCheck.getLunchTime() || lunchTime < toCheck.getEndLunch()) {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        divingRate = round(divingRate, 2);
        return "Student{" + "name=" + name + ", divingRate=" + divingRate + ", lunchTime=" + lunchTime + ", lunchPeriod=" + lunchPeriod + '}';
    }
    
    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
    
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}