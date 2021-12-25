package com.projects.gamcare.models;

import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;

import java.util.List;
import java.util.Objects;

public class Doctor extends Model implements ModelInterface {
    protected final String table = "doctors";

    public String getTableName() {
        return table;
    }





    public List<String> getAvailableSeniors(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableJuniors(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableStudents(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    private List<Model> getOtherHospitalDoctors(Integer hospitalId, String level) {
        return database
            .select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("hospitals_doctors.hospitals_id", "!=", hospitalId)
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .getAll();
    }





    public List<String> getAvailableSeniors() {
        List<Model> doctors = getOtherHospitalDoctors(DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableJuniors() {
        List<Model> doctors = getOtherHospitalDoctors(DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableStudents() {
        List<Model> doctors = getOtherHospitalDoctors(DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    private List<Model> getOtherHospitalDoctors(String level) {
        return database
            .select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .getAll();
    }





    private List<String> getAvailable(List<Model> doctors) {
        return doctors.stream()
            .filter(doctor -> isAvailable(doctor, doctors))
            .map(Model::fullNameAttribute)
            .toList();
    }

    private static Boolean isAvailable(Model doctor, List<Model> doctors) {
        return doctors.stream()
            .filter(filteredDoctor -> Objects.equals(filteredDoctor.attributes.get("id"), doctor.attributes.get("id")))
            .count() < 3;
    }
}
