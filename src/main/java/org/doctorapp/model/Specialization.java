package org.doctorapp.model;

public enum Specialization {
    ORTHO("ORTHOPEDIC"),
    PEDIA("PEDIATRICIAN"),
    PHYSICIAN("GENERAL PHYSICIAN"),
    GYNAE("GYNAECOLOGIST"),
    NEURO("NEUROLOGIST"),
    DERMA("DERMATOLOGIST");

    private String speciality;
    Specialization(String speciality){
        this.speciality=speciality;
    }
    public String getSpeciality(){
        return speciality;
    }
}
