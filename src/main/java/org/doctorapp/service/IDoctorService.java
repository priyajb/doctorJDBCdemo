package org.doctorapp.service;

import org.doctorapp.exceptions.DoctorNotFoundExceptions;
import org.doctorapp.exceptions.IdNotFoundException;
import org.doctorapp.model.Doctor;

import java.util.List;

public interface IDoctorService {
    void addDoctor(Doctor doctor);
    void updateDoctor(int doctorId,double fees);
    void deleteDoctor(int doctorId);
    Doctor getById(int doctorId) throws IdNotFoundException;

    List<Doctor> getSpeciality(String speciality) throws DoctorNotFoundExceptions;
    List<Doctor> getAll() throws DoctorNotFoundExceptions;
    List<Doctor> getBySpecialityAndExp(String speciality,int experience) throws DoctorNotFoundExceptions;
    List<Doctor> getBySpecialityAndLessFees(String speciality,double fees) throws DoctorNotFoundExceptions;
    List<Doctor> getBySpecialityAndRatings(String speciality, int ratings) throws DoctorNotFoundExceptions;
    List<Doctor> getSpecialityAndNameContains(String speciality,String doctorName) throws DoctorNotFoundExceptions;
}
