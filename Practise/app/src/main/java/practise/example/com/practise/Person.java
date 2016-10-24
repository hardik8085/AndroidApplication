package practise.example.com.practise;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Hardik Thakkar on 2/1/2016.
 */
public class Person implements Parcelable {
    String name;
    String surname;
    int age;
    public Person(String name, String surname, int age) {

        this.name = name;
        this.surname = surname;
        this.age = age;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(name);
            dest.writeString(surname);
            dest.writeInt(age);
        }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    public static final Parcelable.Creator<Person> CREATOR
            = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    private Person(Parcel in) {
        this.name=in.readString();
        this.surname=in.readString();
        this.age=in.readInt();
    }


}
