package org.doctorapp.service;

import org.doctorapp.exceptions.DoctorNotFoundExceptions;
import org.doctorapp.exceptions.IdNotFoundException;
import org.doctorapp.model.Doctor;
import org.doctorapp.repository.DoctorRepositoryImpl;
import org.doctorapp.repository.IDoctorRepository;

import java.util.Collections;
import java.util.List;

public class DoctorServiceImpl implements IDoctorService {
    private IDoctorRepository doctorRepository= new DoctorRepositoryImpl();
    @Override
    public void addDoctor(Doctor doctor) {
        doctorRepository.addDoctor(doctor);
    }

    @Override
    public void updateDoctor(int doctorId, double fees) {
        doctorRepository.updateDoctor(doctorId,fees);
    }

    @Override
    public void deleteDoctor(int doctorId) {
        doctorRepository.deleteDoctor(doctorId);

    }

    @Override
    public Doctor getById(int doctorId) throws IdNotFoundException {
        Doctor doctor= doctorRepository.findById(doctorId);
        if(doctor==null)
            throw new IdNotFoundException("Invalid Id");
        return doctor;
    }

    @Override
    public List<Doctor> getSpeciality(String speciality) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = doctorRepository.findSpeciality(speciality);
        if(doctorList.isEmpty())
            throw new DoctorNotFoundExceptions("Doctors are not available with this speciality");
        Collections.sort(doctorList,(d1, d2)->(d1.getDoctorName().compareTo(d2.getDoctorName())));
        return doctorList;
    }

    @Override
    public List<Doctor> getAll() throws DoctorNotFoundExceptions {
        List<Doctor> doctorList=doctorRepository.findAll();
        return doctorList;
    }

    @Override
    public List<Doctor> getBySpecialityAndExp(String speciality, int experience) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = doctorRepository.findBySpecialityAndExp(speciality,experience);
        if(doctorList.isEmpty())
            throw new DoctorNotFoundExceptions("Doctors are not available with this speciality and Experience");
        Collections.sort(doctorList,(d1,d2)->((Integer)d2.getExperience()).compareTo(d1.getExperience()));
        return doctorList;
    }

    @Override
    public List<Doctor> getBySpecialityAndLessFees(String speciality, double fees) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = doctorRepository.findBySpecialityAndLessFees(speciality,fees);
        if(doctorList.isEmpty())
            throw new DoctorNotFoundExceptions("Doctors are not available with this speciality and Fees");
        Collections.sort(doctorList,(d1,d2)->((Double)d1.getFees()).compareTo(d2.getFees()));
        return doctorList;
    }

    @Override
    public List<Doctor> getBySpecialityAndRatings(String speciality, int ratings) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = doctorRepository.findBySpecialityAndRatings(speciality,ratings);
        if(doctorList.isEmpty())
            throw new DoctorNotFoundExceptions("Doctors are not available with this speciality and Ratings");
        Collections.sort(doctorList,(d1,d2)->((Integer)d1.getRatings()).compareTo(d2.getRatings()));
        return doctorList;

    }

    @Override
    public List<Doctor> getSpecialityAndNameContains(String speciality, String doctorName) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = doctorRepository.findSpecialityAndNameContains(speciality,doctorName);
        if(doctorList.isEmpty())
            throw new DoctorNotFoundExceptions("Doctors are not available with this speciality and Name");
        Collections.sort(doctorList,(d1,d2)->((Integer)d1.getDoctorName().compareTo(d2.getDoctorName())));
        return doctorList;

    }
}
