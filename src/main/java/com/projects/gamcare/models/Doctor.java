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

    private List<Model> getAvailable(List<Model> doctors) {
        return doctors.stream()
            .filter(doctor -> belongsToLessThanThreeHospitals(doctor, doctors))
            .distinct()
            .toList();
    }


    /**
     * Get Available Doctors for Hospital
     */
    public List<Model> getAvailableSeniors(Hospital hospital) {
        List<Model> doctors = getAllWhere(DoctorLevel.SENIOR.name());

        return getAvailable(doctors, hospital);
    }

    public List<Model> getAvailableJuniors(Hospital hospital) {
        List<Model> doctors = getAllWhere(DoctorLevel.JUNIOR.name());

        return getAvailable(doctors, hospital);
    }

    public List<Model> getAvailableStudents(Hospital hospital) {
        List<Model> doctors = getAllWhere(DoctorLevel.STUDENT.name());

        return getAvailable(doctors, hospital);
    }

    private List<Model> getAvailable(List<Model> doctors, Hospital hospital) {
        return doctors.stream()
            .filter(doctor -> belongsToLessThanThreeHospitals(doctor, doctors))
            .filter(doctor -> belongsToOtherHospitalsExcept(doctor, hospital))
            .distinct()
            .toList();
    }

    private static Boolean belongsToOtherHospitalsExcept(Model doctor, Hospital hospital) {
        List<Integer> hospitalDoctorsIDs = hospital.getDoctors().stream().map(Model::idAttribute).toList();

        return ! hospitalDoctorsIDs.contains(doctor.idAttribute());
    }


    /**
     * General Methods
     */
    public List<Model> getAllWhere(String level) {
        return getDatabase()
            .select(List.of("*"))
            .with("users")
            .where("doctors.career_level", level)
            .orderBy("first_name")
            .getAll();
    }

    private static Boolean belongsToLessThanThreeHospitals(Model doctor, List<Model> doctors) {
        return doctors.stream()
            .filter(filteredDoctor -> Objects.equals(filteredDoctor.idAttribute(), doctor.idAttribute()))
            .count() < 3;
    }


    /**
     * Query Methods
     */
    public List<Model> getHospitals() {
        return (new Hospital())
            .getDatabase()
            .select(List.of("*"))
            .with("hospitals_doctors")
            .where("hospitals_doctors.doctors_id", idAttribute())
            .getAll();
    }
}
