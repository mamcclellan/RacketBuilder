package edu.depaul.csc472.m.mcclellan.mcclellanmracketbuilder;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

class Profile implements Parcelable, Serializable {

    // Profile variables
    private String name;
    private int id;

    // Racket variables
    private int headSize;
    private int weight;
    private int balance;
    private int length;
    private int stiffness;

    // String variables
    private int numMains;
    private int numCrosses;
    private int tension;
    private int typeMain;
    private int typeCrosses;
    private int gauge;
    private long updateTime;

    Profile(String name, int id, int headSize,
            int weight, int balance, int length,
            int stiffness, int numMains, int numCrosses,
            int tension, int typeMain, int typeCrosses,
            int gauge, long updateTime)
    {
        this.name = name;
        this.id = id;
        this.headSize = headSize;
        this.weight = weight;
        this.balance = balance;
        this.length = length;
        this.stiffness = stiffness;
        this.numMains = numMains;
        this.numCrosses = numCrosses;
        this.tension = tension;
        this.typeMain = typeMain;
        this.typeCrosses = typeCrosses;
        this.gauge = gauge;
        this.updateTime = updateTime;
    }

    void setProfile(Profile in)
    {
        this.name = in.name;
        this.id = in.id;
        this.headSize = in.headSize;
        this.weight = in.weight;
        this.balance = in.balance;
        this.length = in.length;
        this.stiffness = in.stiffness;
        this.numMains = in.numMains;
        this.numCrosses = in.numCrosses;
        this.tension = in.tension;
        this.typeMain = in.typeMain;
        this.typeCrosses = in.typeCrosses;
        this.gauge = in.gauge;
        this.updateTime = in.updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int getHeadSize() {
        return headSize;
    }

    void setHeadSize(int headSize) {
        this.headSize = headSize;
    }

    int getWeight() {
        return weight;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    int getBalance() {
        return balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }

    int getLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }

    int getStiffness() {
        return stiffness;
    }

    void setStiffness(int stiffness) {
        this.stiffness = stiffness;
    }

    int getNumMains() {
        return numMains;
    }

    void setNumMains(int numMains) {
        this.numMains = numMains;
    }

    int getNumCrosses() {
        return numCrosses;
    }

    void setNumCrosses(int numCrosses) {
        this.numCrosses = numCrosses;
    }

    int getTension() {
        return tension;
    }

    void setTension(int tension) {
        this.tension = tension;
    }

    int getTypeMain() {
        return typeMain;
    }

    void setTypeMain(int typeMain) {
        this.typeMain = typeMain;
    }

    int getTypeCrosses() {
        return typeCrosses;
    }

    void setTypeCrosses(int typeCrosses) {
        this.typeCrosses = typeCrosses;
    }

    int getGauge() {
        return gauge;
    }

    void setGauge(int gauge) {
        this.gauge = gauge;
    }

    long getUpdateTime() {
        return updateTime;
    }

    void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private Profile(Parcel source) {
        this.name = source.readString();
        this.headSize = source.readInt();
        this.weight = source.readInt();
        this.balance = source.readInt();
        this.length = source.readInt();
        this.stiffness = source.readInt();
        this.numMains = source.readInt();
        this.numCrosses = source.readInt();
        this.tension = source.readInt();
        this.typeMain = source.readInt();
        this.typeCrosses = source.readInt();
        this.gauge = source.readInt();
        this.updateTime = source.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(headSize);
        dest.writeInt(weight);
        dest.writeInt(balance);
        dest.writeInt(length);
        dest.writeInt(stiffness);
        dest.writeInt(numMains);
        dest.writeInt(numCrosses);
        dest.writeInt(tension);
        dest.writeInt(typeMain);
        dest.writeInt(typeCrosses);
        dest.writeInt(gauge);
        dest.writeLong(updateTime);
    }

    public static final Creator<Profile> CREATOR
            = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
