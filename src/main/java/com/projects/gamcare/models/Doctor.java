package com.projects.gamcare.models;

import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.models.main.BaseModel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Doctor extends BaseModel {
    private static final String table = "doctors";

//    public static List<String> getItems() {
//        return getNames(table);
//    }
//
//
//
//
//
//    public static List<String> getAvailableSeniors(Integer hospitalId) {
//        List<Map<String, Object>> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.SENIOR.name());
//
//        return getAvailable(doctors);
//    }
//
//    public static List<String> getAvailableJuniors(Integer hospitalId) {
//        List<Map<String, Object>> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.JUNIOR.name());
//
//        return getAvailable(doctors);
//    }
//
//    public static List<String> getAvailableStudents(Integer hospitalId) {
//        List<Map<String, Object>> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.STUDENT.name());
//
//        return getAvailable(doctors);
//    }
//
//    private static List<Map<String, Object>> getOtherHospitalDoctors(Integer hospitalId, String level) {
//        List<String> fields = List.of("*");
//
//        return database()
//            .select(fields)
//            .from("doctors")
//            .with("users")
//            .with("hospitals_doctors")
//            .where("hospitals_doctors.hospitals_id", "!=", hospitalId.toString())
//            .where("doctors.career_level", "=", level)
//            .orderBy("first_name")
//            .get();
//    }
//
//
//
//
//
//    public static List<String> getAvailableSeniors() {
//        List<Map<String, Object>> doctors = getOtherHospitalDoctors(DoctorLevel.SENIOR.name());
//
//        return getAvailable(doctors);
//    }
//
//    public static List<String> getAvailableJuniors() {
//        List<Map<String, Object>> doctors = getOtherHospitalDoctors(DoctorLevel.JUNIOR.name());
//
//        return getAvailable(doctors);
//    }
//
//    public static List<String> getAvailableStudents() {
//        List<Map<String, Object>> doctors = getOtherHospitalDoctors(DoctorLevel.STUDENT.name());
//
//        return getAvailable(doctors);
//    }
//
//    private static List<Map<String, Object>> getOtherHospitalDoctors(String level) {
//        List<String> fields = List.of("*");
//
//        return database()
//            .select(fields)
//            .from("doctors")
//            .with("users")
//            .with("hospitals_doctors")
//            .where("doctors.career_level", "=", level)
//            .orderBy("first_name")
//            .get();
//    }
//
//
//
//
//
//    private static List<String> getAvailable(List<Map<String, Object>> doctors) {
//        return doctors.stream()
//            .filter(doctor -> isAvailable(doctor, doctors))
//            .map(Doctor::getFullName)
//            .toList();
//    }
//
//    private static Boolean isAvailable(Map<String, Object> doctor, List<Map<String, Object>> doctors) {
//        return doctors.stream()
//            .filter(filteredDoctor -> Objects.equals(filteredDoctor.get("id"), doctor.get("id")))
//            .count() < 3;
//    }
//
//    private static String getFullName(Map<String, Object> doctor) {
//        return doctor.get("first_name") + " " + doctor.get("last_name");
//    }
}
