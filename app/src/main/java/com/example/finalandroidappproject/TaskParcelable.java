package com.example.finalandroidappproject;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;



public class TaskParcelable implements Parcelable {
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
    public static final Parcelable.Creator<TaskParcelable> CREATOR = new Parcelable.Creator<TaskParcelable>() {

        @Override
        public TaskParcelable createFromParcel(Parcel parcel) { return new TaskParcelable(parcel);}

        @Override
        public TaskParcelable[] newArray(int size) { return new TaskParcelable[0];}
    };

    /**Constructor
     *
     * @param parcel
     */
    public TaskParcelable(Parcel parcel){
        taskName = parcel.readString();
        key = parcel.readString();
    }

    /**Constructor
     *
     * @param taskName
     */
    public TaskParcelable(String taskName){
        this.taskName = taskName;
        this.key = "no key yet";
    }

    /**Constructor
     *
     * @param taskName
     * @param key
     */
    public TaskParcelable(String taskName, String key){
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


    //The regular Java functions
    public boolean equals(TaskParcelable other) {
        return this.taskName.equals(other.taskName);
    }

    /**getters and setters
     *
     */

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