package org.doctorapp.repository;

import org.doctorapp.exceptions.DoctorNotFoundExceptions;
import org.doctorapp.exceptions.IdNotFoundException;
import org.doctorapp.model.Doctor;

import java.util.List;

public interface IDoctorRepository {
    void addDoctor(Doctor doctor);
    void updateDoctor(int doctorId,double fees);
    void deleteDoctor(int doctorId);
    Doctor findById(int doctorId) throws IdNotFoundException;

    List<Doctor> findSpeciality(String speciality) throws DoctorNotFoundExceptions;
    List<Doctor> findAll() throws DoctorNotFoundExceptions;
    List<Doctor> findBySpecialityAndExp(String speciality,int experience) throws DoctorNotFoundExceptions;
    List<Doctor> findBySpecialityAndLessFees(String speciality,double fees) throws DoctorNotFoundExceptions;
    List<Doctor> findBySpecialityAndRatings(String speciality, int ratings) throws DoctorNotFoundExceptions;
    List<Doctor> findSpecialityAndNameContains(String speciality,String doctorName) throws DoctorNotFoundExceptions;
}
