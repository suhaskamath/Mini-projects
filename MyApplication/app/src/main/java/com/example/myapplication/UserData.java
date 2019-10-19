package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class UserData implements Parcelable {
    private String email,gender,address,name;
    private Float marks1,marks2,marks3;
    private Boolean verify,placed;
    private Map<String,String>company;

    public UserData(Parcel in) {
        name=in.readString();
        email=in.readString();
        gender=in.readString();
        marks1=in.readFloat();
        marks2=in.readFloat();
        marks3=in.readFloat();
        address=in.readString();
        verify=Boolean.parseBoolean(in.readString());
        company=null;
        placed=Boolean.parseBoolean(in.readString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerify() {
        return verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    public UserData(){
        this.email = null;
        this.gender = null;
        this.address = null;
        this.marks1 = 0.0f;
        this.marks2 = 0.0f;
        this.marks3 = 0.0f;
        this.name=null;
        this.verify=null;
        company=new Map<String, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsValue(@Nullable Object o) {
                return false;
            }

            @Nullable
            @Override
            public String get(@Nullable Object o) {
                return null;
            }

            @Nullable
            @Override
            public String put(String s, String s2) {
                return null;
            }

            @Nullable
            @Override
            public String remove(@Nullable Object o) {
                return null;
            }

            @Override
            public void putAll(@NonNull Map<? extends String, ? extends String> map) {

            }

            @Override
            public void clear() {

            }

            @NonNull
            @Override
            public Set<String> keySet() {
                return null;
            }

            @NonNull
            @Override
            public Collection<String> values() {
                return null;
            }

            @NonNull
            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        };
    }
    public Boolean getPlaced() {
        return placed;
    }

    public void setPlaced(Boolean placed) {
        this.placed = placed;
    }
    public UserData(String name, String email, String gender, String address, Float marks1, Float marks2, Float marks3, Boolean verify,Map<String,String>company,Boolean placed) {
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.marks1 = marks1;
        this.marks2 = marks2;
        this.marks3 = marks3;
        this.name=name;
        this.verify=verify;
        this.company=company;
        this.placed=placed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public Map<String, String> getCompany() {
        return company;
    }

    public void setCompany(Map<String, String> company) {
        this.company = company;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getMarks1() {
        return marks1;
    }

    public void setMarks1(Float marks1) {
        this.marks1 = marks1;
    }

    public Float getMarks2() {
        return marks2;
    }

    public void setMarks2(Float marks2) {
        this.marks2 = marks2;
    }

    public Float getMarks3() {
        return marks3;
    }

    public void setMarks3(Float marks3) {
        this.marks3 = marks3;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<UserData> CREATOR = new Creator<UserData>() {
        @Override
        public UserData createFromParcel(Parcel in) {
            return new UserData(in);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(gender);
        parcel.writeFloat(marks1);
        parcel.writeFloat(marks2);
        parcel.writeFloat(marks3);
        parcel.writeString(address);
        parcel.writeString(verify.toString());
        parcel.writeString(placed.toString());
       // parcel.writeMap(company);


    }
}
