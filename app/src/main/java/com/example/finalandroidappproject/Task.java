package com.example.finalandroidappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable {
    private String taskName;
    private String key;

    /**
     * This method is required for Parceable interface.  As of now, this method is in the default state
     * and doesn't really do anything.
     *
     * If your Parcelable class will have child classes, you'll need to
     *          * take some extra care with the describeContents() method. This will
     *          * let you identify the specific child class that should be created by
     *          * the Parcelable.Creator. You can read more about how this works on
     *          * Stack Overflow.
     *          *
     *          * https://stackoverflow.com/questions/4778834/purpose-of-describecontents-of-parcelable-interface
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    //Required for Parcelable to work
    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {

        @Override
        public Task createFromParcel(Parcel parcel) { return new Task(parcel);}

        @Override
        public Task[] newArray(int size) { return new Task[0];}
    };

    /**Constructor
     *
     * @param parcel
     */
    public Task(Parcel parcel){
        taskName = parcel.readString();
        key = parcel.readString();
    }

    /**
     *
     * @param taskName
     */
    public Task(String taskName){
        this.taskName = taskName;
        this.key = "no key yet";
    }

    /**
     *
     * @param taskName
     * @param key
     */
    public Task(String taskName, String key){
        this.taskName = taskName;
        this.key = key;
    }

    @Override
    /**
     * This is what is used when we send the Event object through an intent
     * It is also a method that is part of the Parceable interface and is needed
     * to set up the object that is being sent.  Then, when it is received, the
     * other Event constructor that accepts a Parcel reference can "unpack it"
     *
     */
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(taskName);
        dest.writeString(key);
    }

    public boolean equals(Task other) {
        return this.taskName.equals(other.taskName);
    }

    //getters and setters
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return key;
    }

    public String getTaskName(){
        return taskName;
    }


}