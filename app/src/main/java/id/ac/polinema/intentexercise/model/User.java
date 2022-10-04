package id.ac.polinema.intentexercise.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String profile;
    private String fullname;
    private String email;
    private String password;
    private String homepage;
    private String about;

    public User(String profile, String fullname, String email, String password, String homepage, String about) {
        this.profile = profile;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.homepage = homepage;
        this.about = about;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.profile);
        dest.writeString(this.fullname);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.homepage);
        dest.writeString(this.about);
    }

    public void readFromParcel(Parcel source) {
        this.profile = source.readString();
        this.fullname = source.readString();
        this.email = source.readString();
        this.password = source.readString();
        this.homepage = source.readString();
        this.about = source.readString();
    }

    protected User(Parcel in) {
        this.profile = in.readString();
        this.fullname = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.homepage = in.readString();
        this.about = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
