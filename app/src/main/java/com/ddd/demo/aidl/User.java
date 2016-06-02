package com.ddd.demo.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: doushuqi
 * Date: 15/8/28
 * Time: 下午4:59
 * Email: shuqi.dou@singuloid.com
 */
public class User implements Parcelable {
    private long id;
    private int age;
    private String phone;
    private boolean registered;

    // No-arg Ctor
    public User() {
    }

    // all getters and setters go here //...

    /**
     * Used to give additional hints on how to process the received parcel.
     */
    @Override
    public int describeContents() {
        // ignore for now
        return 0;
    }

    @Override
    public void writeToParcel(Parcel pc, int flags) {
        pc.writeLong(id);
        pc.writeInt(age);
        pc.writeString(phone);
        pc.writeInt(registered ? 1 : 0);
    }

    /**
     * Static field used to regenerate object, individually or as arrays
     */
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel pc) {
            return new User(pc);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /**
     * Ctor from Parcel, reads back fields IN THE ORDER they were written
     */
    public User(Parcel pc) {
        id = pc.readLong();
        age = pc.readInt();
        phone = pc.readString();
        registered = (pc.readInt() == 1);
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id = " + id
                + "\nage = " + age
                + "\nphone = " + phone
                + "\nregistered = " + registered;
    }
}
