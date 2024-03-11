package org.doctorapp.repository;

import org.doctorapp.exceptions.DoctorNotFoundExceptions;
import org.doctorapp.exceptions.IdNotFoundException;
import org.doctorapp.model.Doctor;
import org.doctorapp.util.DoctorDb;
import org.doctorapp.util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class DoctorRepositoryImpl implements IDoctorRepository{
    @Override
    public void addDoctor(Doctor doctor) {
        try(Connection connection= DoctorDb.openConnection();
            PreparedStatement statement=connection.prepareStatement(Queries.INSERTQUERY)){
            statement.setString(1,doctor.getDoctorName());
            statement.setString(2,doctor.getSpeciality());
            statement.setInt(3,doctor.getExperience());
            statement.setInt(4,doctor.getRatings());
            statement.setDouble(5,doctor.getFees());
            boolean result = statement.execute();
            System.out.println("Rows inserted successfully"+result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateDoctor(int doctorId, double fees) {
        try(Connection connection= DoctorDb.openConnection();
            PreparedStatement statement=connection.prepareStatement(Queries.UPDATEQUERY)){
            statement.setDouble(1,fees);
            statement.setInt(2,doctorId);
            boolean result = statement.execute();
            System.out.println("Rows Updated successfully"+result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteDoctor(int doctorId) {
        try(Connection connection= DoctorDb.openConnection();
            PreparedStatement statement=connection.prepareStatement(Queries.DELETEQUERY)){
            statement.setInt(1,doctorId);
            boolean result = statement.execute();
            System.out.println("Row deleted successfully"+result);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Doctor findById(int doctorId) throws IdNotFoundException {
        try (Connection connection = DoctorDb.openConnection();
             PreparedStatement statement = connection.prepareStatement(Queries.FINDBYID)) {
            statement.setInt(1, doctorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String doctorName = resultSet.getString("doctor_name");
                    String speciality = resultSet.getString("speciality");
                    int experience = resultSet.getInt("experience");
                    int ratings = resultSet.getInt("ratings");
                    double fees = resultSet.getDouble("fees");
                    Doctor  doctor = new Doctor(doctorId, doctorName, speciality, fees, ratings, experience);
                    return doctor;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> findSpeciality(String speciality) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = new ArrayList<>();
        try(Connection connection=DoctorDb.openConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECIALITY)){
            statement.setString(1,speciality);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    String doctorName = resultSet.getString("doctor_name");
                    int experience = resultSet.getInt("experience");
                    int ratings =resultSet.getInt("ratings");
                    int doctorId = resultSet.getInt("doctor_id");
                    double fees = resultSet.getDouble("fees");
                    Doctor doctor=new Doctor();
                    doctor.setDoctorName(doctorName);
                    doctor.setSpeciality(speciality);
                    doctor.setRatings(ratings);
                    doctor.setDoctorId(doctorId);
                    doctor.setExperience(experience);
                    doctor.setFees(fees);

                    doctorList.add(doctor);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public List<Doctor> findAll() throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = new ArrayList<>();
        try(Connection connection=DoctorDb.openConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.FINDALLQUERY)){
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    int doctorId = resultSet.getInt("doctor_id");
                    String doctorName = resultSet.getString("doctor_name");
                    String speciality= resultSet.getString("speciality");
                    int experience = resultSet.getInt("experience");
                    int ratings =resultSet.getInt("ratings");
                    double fees = resultSet.getDouble("fees");
                    System.out.println(doctorId+"\t"+doctorName+"\t"+speciality+"\t"+experience+"\t"+fees+"\t"+ratings);
                    Doctor doctor=new Doctor(doctorId,doctorName,speciality,fees,ratings,experience);

                    doctorList.add(doctor);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public List<Doctor> findBySpecialityAndExp(String speciality, int experience) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = new ArrayList<>();
        try(Connection connection=DoctorDb.openConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDEX)){
            statement.setString(1,speciality);
            statement.setInt(2,experience);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    String doctorName = resultSet.getString("doctor_name");
                    speciality = resultSet.getString("speciality");
                    experience = resultSet.getInt("experience");
                    int ratings =resultSet.getInt("ratings");
                    int doctorId = resultSet.getInt("doctor_id");
                    double fees = resultSet.getDouble("fees");
                    Doctor doctor=new Doctor();
                    doctor.setDoctorName(doctorName);
                    doctor.setSpeciality(speciality);
                    doctor.setRatings(ratings);
                    doctor.setDoctorId(doctorId);
                    doctor.setExperience(experience);
                    doctor.setFees(fees);

                    doctorList.add(doctor);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public List<Doctor> findBySpecialityAndLessFees(String speciality, double fees) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = new ArrayList<>();
        try(Connection connection=DoctorDb.openConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDFEES)){
            statement.setString(1,speciality);
            statement.setDouble(2,fees);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    String doctorName = resultSet.getString("doctor_name");
                    speciality = resultSet.getString("speciality");
                    int experience = resultSet.getInt("experience");
                    int ratings =resultSet.getInt("ratings");
                    int doctorId = resultSet.getInt("doctor_id");
                    fees = resultSet.getDouble("fees");
                    Doctor doctor=new Doctor();
                    doctor.setDoctorName(doctorName);
                    doctor.setSpeciality(speciality);
                    doctor.setRatings(ratings);
                    doctor.setDoctorId(doctorId);
                    doctor.setExperience(experience);
                    doctor.setFees(fees);

                    doctorList.add(doctor);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public List<Doctor> findBySpecialityAndRatings(String speciality, int ratings) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = new ArrayList<>();
        try(Connection connection=DoctorDb.openConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDRATINGS)){
            statement.setString(1,speciality);
            statement.setInt(2,ratings);
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    String doctorName = resultSet.getString("doctor_name");
                    speciality = resultSet.getString("speciality");
                    int experience = resultSet.getInt("experience");
                    ratings =resultSet.getInt("ratings");
                    int doctorId = resultSet.getInt("doctor_id");
                    double fees = resultSet.getDouble("fees");
                    Doctor doctor=new Doctor();
                    doctor.setDoctorName(doctorName);
                    doctor.setSpeciality(speciality);
                    doctor.setRatings(ratings);
                    doctor.setDoctorId(doctorId);
                    doctor.setExperience(experience);
                    doctor.setFees(fees);

                    doctorList.add(doctor);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }

    @Override
    public List<Doctor> findSpecialityAndNameContains(String speciality, String doctorName) throws DoctorNotFoundExceptions {
        List<Doctor> doctorList = new ArrayList<>();
        try(Connection connection=DoctorDb.openConnection();
            PreparedStatement statement = connection.prepareStatement(Queries.FINDBYSPECANDNAME)){
            statement.setString(1,speciality);
            statement.setString(2,"%"+doctorName+"%");
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()){
                    doctorName = resultSet.getString("doctor_name");
                    speciality = resultSet.getString("speciality");
                    int experience = resultSet.getInt("experience");
                    int ratings =resultSet.getInt("ratings");
                    int doctorId = resultSet.getInt("doctor_id");
                    double fees = resultSet.getDouble("fees");
                    Doctor doctor=new Doctor();
                    doctor.setDoctorName(doctorName);
                    doctor.setSpeciality(speciality);
                    doctor.setRatings(ratings);
                    doctor.setDoctorId(doctorId);
                    doctor.setExperience(experience);
                    doctor.setFees(fees);

                    doctorList.add(doctor);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctorList;
    }
}
