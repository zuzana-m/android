package org.a5calls.android.a5calls.test;

import android.os.Parcel;

import org.a5calls.android.a5calls.test.Mocks.MockParcel;
import org.a5calls.android.a5calls.model.Contact;
import org.a5calls.android.a5calls.model.FieldOffice;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class ContactModelTest {

    @Test
    public void testContactIsParcelable() {
        FieldOffice[] fieldOffices = new FieldOffice[] {new FieldOffice("city","phone")};
        Contact contact = new Contact("id","name","phone","photoURL", "party",
                "state", "reason", "area", fieldOffices);

        Parcel parcel = MockParcel.obtain();
        contact.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Contact createdFromParcel = Contact.CREATOR.createFromParcel(parcel);

        Assert.assertThat(createdFromParcel.reason, is("reason"));
        Assert.assertThat(createdFromParcel.area, is("area"));
        Assert.assertThat(createdFromParcel.id, is("id"));
        Assert.assertThat(createdFromParcel.name, is("name"));
        Assert.assertThat(createdFromParcel.party, is("party"));
        Assert.assertThat(createdFromParcel.phone, is("phone"));
        Assert.assertThat(createdFromParcel.photoURL, is("photoURL"));
        Assert.assertThat(createdFromParcel.state, is("state"));
        Assert.assertThat(createdFromParcel.field_offices[0].city, is(fieldOffices[0].city));
    }
}
