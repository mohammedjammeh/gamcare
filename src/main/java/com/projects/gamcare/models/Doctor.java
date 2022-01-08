package com.projects.gamcare.models;

import com.projects.gamcare.core.StringTool;
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


    /**
     * Attributes Methods
     */
    public String careerLevelAttribute() {
        String careerLevel = (String) attributes.get("career_level");

        return StringTool.capitalise(careerLevel);
    }


    /**
     * Get Available Doctors
     */
    public List<String> getAvailableSeniors() {
        List<Model> doctors = getAllWhere(DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableJuniors() {
        List<Model> doctors = getAllWhere(DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableStudents() {
        List<Model> doctors = getAllWhere(DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    public List<Model> getAllWhere(String level) {
        return getDatabase()
            .select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .getAll();
    }


    /**
     * Get Available Doctors for Hospital
     */
    public List<String> getAvailableSeniors(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctorsWhere(hospitalId, DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableJuniors(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctorsWhere(hospitalId, DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableStudents(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctorsWhere(hospitalId, DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    private List<Model> getOtherHospitalDoctorsWhere(Integer hospitalId, String level) {
        return database
            .select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("hospitals_doctors.hospitals_id", "!=", hospitalId)
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .getAll();
    }


    /**
     * General Methods
     */
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

    /**
     * Query Methods
     */
    public Doctor where(String column, Object value) {
        return (Doctor) super.where(column, value);
    }

    public List<Model> getHospitals() {
        return (new Hospital())
            .getDatabase()
            .select(List.of("*"))
            .with("hospitals_doctors")
            .where("hospitals_doctors.doctors_id", "=", idAttribute())
            .getAll();
    }
}
