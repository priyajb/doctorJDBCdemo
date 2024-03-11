package org.doctorapp.client;

import org.doctorapp.model.Doctor;
import org.doctorapp.service.DoctorServiceImpl;
import org.doctorapp.service.IDoctorService;

import java.util.Scanner;

import static java.lang.System.exit;

public class Client {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        IDoctorService doctorService= new DoctorServiceImpl();
        System.out.println("1. Update  " +
                "2. Delete  " +
                "3. List of All doctors details  " +
                "4. Find by speciality  " +
                "5. Find by speciality and Experience  " +
                "6. Find by speciality and Ratings  " +
                "7. Find by speciality and Fees  " +
                "8. Find by speciality and Name  " +
                "9. Find by DoctorId  " +
                "10. Exit \n");
        try {
            System.out.println("Enter your choice");
            int choice =scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the details to update");
                    System.out.println("Enter doctorId");
                    int doctorId = scanner.nextInt();
                    System.out.println("Enter the Fees to update");
                    double fees = scanner.nextDouble();
                    doctorService.updateDoctor(doctorId,fees);
                    break;

                case 2:
                    System.out.println("Enter the details to delete");
                    System.out.println("Enter doctorId");
                    int UpdateDoctorId =scanner.nextInt();
                    doctorService.deleteDoctor(UpdateDoctorId);
                    break;

                case 3:
                    System.out.println("Finding all doctor details");
                    doctorService.getAll();
                    break;

                case 4:
                    System.out.println("Finding the doctors by speciality");
                    System.out.println("Enter Doctor Speciality");
                    String findBySpeciality = scanner.next();
                    for(Doctor doc: doctorService.getSpeciality(findBySpeciality))
                        System.out.println(doc);
                    break;

                case 5:
                    System.out.println("Enter details to find the doctor by Speciality and Experience");
                    System.out.println("Enter Speciality");
                    String findBySpec = scanner.next();
                    System.out.println("Enter Experience");
                    int findByExperience = scanner.nextInt();
                    for(Doctor doc: doctorService.getBySpecialityAndExp(findBySpec,findByExperience))
                        System.out.println(doc);
                    break;

                case 6:
                    System.out.println("Enter details to find the doctor by Speciality and Ratings");
                    System.out.println("Enter Speciality");
                    String findBySpecialty = scanner.next();
                    System.out.println("Enter Ratings");
                    int findByRatings = scanner.nextInt();
                    for(Doctor doc: doctorService.getBySpecialityAndRatings(findBySpecialty,findByRatings))
                        System.out.println(doc);
                    break;

                case 7:
                    System.out.println("Enter details to find the doctor by speciality and Fees");
                    System.out.println("Enter Speciality");
                    String findBySpecia = scanner.next();
                    System.out.println("Enter Fees");
                    double findByFees = scanner.nextDouble();
                    for(Doctor doc: doctorService.getBySpecialityAndLessFees(findBySpecia,findByFees))
                        System.out.println(doc);
                    break;

                case 8:
                    System.out.println("Enter details to find the doctor by speciality and Name");
                    System.out.println("Enter Speciality");
                    String doctorSpeciality = scanner.next();
                    System.out.println("Enter Doctor name");
                    String doctorName = scanner.next();
                    for(Doctor doc: doctorService.getSpecialityAndNameContains(doctorSpeciality,doctorName))
                        System.out.println(doc);
                    break;

                case 9:
                    System.out.println("Enter the details to find doctor");
                    System.out.println("Enter doctor Id");
                    int findByDoctorId = scanner.nextInt();
                    Doctor doctor=doctorService.getById(findByDoctorId);
                    System.out.println(doctor);
                    break;
                case 10:
                    exit(1);
                default:
                    System.out.println("\nInvalid choice\n");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
