package com.projects.gamcare.models;

import com.projects.gamcare.core.DatabaseModel;
import com.projects.gamcare.enums.DoctorLevel;
import com.projects.gamcare.interfaces.Model;
import com.projects.gamcare.models.main.BaseModel;

import java.util.List;
import java.util.Objects;

public class Doctor extends BaseModel implements Model {
    protected final String table = "doctors";

    public String getTableName() {
        return table;
    }





    public List<String> getAvailableSeniors(Integer hospitalId) {
        List<DatabaseModel> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableJuniors(Integer hospitalId) {
        List<DatabaseModel> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableStudents(Integer hospitalId) {
        List<DatabaseModel> doctors = getOtherHospitalDoctors(hospitalId, DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    private List<DatabaseModel> getOtherHospitalDoctors(Integer hospitalId, String level) {
        return select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("hospitals_doctors.hospitals_id", "!=", hospitalId)
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .getAll();
    }





    public List<String> getAvailableSeniors() {
        List<DatabaseModel> doctors = getOtherHospitalDoctors(DoctorLevel.SENIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableJuniors() {
        List<DatabaseModel> doctors = getOtherHospitalDoctors(DoctorLevel.JUNIOR.name());

        return getAvailable(doctors);
    }

    public List<String> getAvailableStudents() {
        List<DatabaseModel> doctors = getOtherHospitalDoctors(DoctorLevel.STUDENT.name());

        return getAvailable(doctors);
    }

    private List<DatabaseModel> getOtherHospitalDoctors(String level) {
        return select(List.of("*"))
            .with("users")
            .with("hospitals_doctors")
            .where("doctors.career_level", "=", level)
            .orderBy("first_name")
            .getAll();
    }





    private List<String> getAvailable(List<DatabaseModel> doctors) {
        return doctors.stream()
            .filter(doctor -> isAvailable(doctor, doctors))
            .map(DatabaseModel::getFullName)
            .toList();
    }

    private static Boolean isAvailable(DatabaseModel doctor, List<DatabaseModel> doctors) {
        return doctors.stream()
            .filter(filteredDoctor -> Objects.equals(filteredDoctor.attributes.get("id"), doctor.attributes.get("id")))
            .count() < 3;
    }
}
