package com.projects.gamcare.models;

import com.projects.gamcare.core.StringTool;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.interfaces.ModelInterface;
import com.projects.gamcare.models.main.Model;
import com.projects.gamcare.models.main.ProfileUser;

import java.util.List;
import java.util.Objects;

public class Doctor extends ProfileUser implements ModelInterface {
    protected final String table = "doctors";

    public String getTableName() {
        return table;
    }


    /**
     * Attributes Methods
     */
    public String careerLevelAttribute() {
        String careerLevel = (String) getAttribute("career_level");

        return StringTool.capitalise(careerLevel);
    }


    /**
     * Get Available Doctors
     */
    public List<Model> getAvailableSeniors() {
        List<Model> doctors = getAllWhere(DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<Model> getAvailableJuniors() {
        List<Model> doctors = getAllWhere(DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<Model> getAvailableStudents() {
        List<Model> doctors = getAllWhere(DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    public List<Model> getAllWhere(String level) {
        return getDatabase()
            .select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("doctors.career_level", level)
            .orderBy("first_name")
            .getAll();
    }


    /**
     * Get Available Doctors for Hospital
     */
    public List<Model> getAvailableSeniors(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctorsWhere(hospitalId, DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<Model> getAvailableJuniors(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctorsWhere(hospitalId, DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<Model> getAvailableStudents(Integer hospitalId) {
        List<Model> doctors = getOtherHospitalDoctorsWhere(hospitalId, DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    private List<Model> getOtherHospitalDoctorsWhere(Integer hospitalId, String level) {
        return database
            .select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .whereNot("hospitals_doctors.hospitals_id", hospitalId)
            .where("doctors.career_level", level)
            .orderBy("first_name")
            .getAll();
    }


    /**
     * General Methods
     */
    private List<Model> getAvailable(List<Model> doctors) {
        return doctors.stream()
            .filter(doctor -> isAvailable(doctor, doctors))
            .distinct()
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
            .where("hospitals_doctors.doctors_id", idAttribute())
            .getAll();
    }
}
