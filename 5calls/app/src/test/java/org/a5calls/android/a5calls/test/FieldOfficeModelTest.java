package org.a5calls.android.a5calls.test;

import android.os.Parcel;

import org.a5calls.android.a5calls.test.Mocks.MockParcel;
import org.a5calls.android.a5calls.model.FieldOffice;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class FieldOfficeModelTest {

    @Test
    public void testFieldOfficeIsParcelable() {
        FieldOffice fieldOffice = new FieldOffice("city", "phone");

        Parcel parcel = MockParcel.obtain();
        fieldOffice.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        FieldOffice createdFromParcel = FieldOffice.CREATOR.createFromParcel(parcel);

        Assert.assertThat(createdFromParcel.city, is("city"));
        Assert.assertThat(createdFromParcel.phone, is("phone"));
    }
}
