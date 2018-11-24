package org.a5calls.android.a5calls.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a contact.
 *
 */
public class Contact implements Parcelable {
    public String id;
    public String name;
    public String phone;
    public String photoURL;
    public String party;
    public String state;
    public String reason;
    public String area;
    public FieldOffice[] field_offices;

    protected Contact(Parcel in) {
        id = in.readString();
        name = in.readString();
        phone = in.readString();
        photoURL = in.readString();
        party = in.readString();
        state = in.readString();
        reason = in.readString();
        area = in.readString();
        field_offices = in.createTypedArray(FieldOffice.CREATOR);
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public Contact(String id, String name, String phone, String photoURL, String party, String state, String reason, String area, FieldOffice[] field_offices) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.photoURL = photoURL;
        this.party = party;
        this.state = state;
        this.reason = reason;
        this.area = area;
        this.field_offices = field_offices;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(photoURL);
        dest.writeString(party);
        dest.writeString(state);
        dest.writeString(reason);
        dest.writeString(area);
        dest.writeTypedArray(field_offices, PARCELABLE_WRITE_RETURN_VALUE);
    }
}
