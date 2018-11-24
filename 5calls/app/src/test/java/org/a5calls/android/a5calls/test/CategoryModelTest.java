package org.a5calls.android.a5calls.test;

import android.os.Parcel;

import org.a5calls.android.a5calls.test.Mocks.MockParcel;
import org.a5calls.android.a5calls.model.Category;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CategoryModelTest {

    @Test
    public void testCategoryIsParcelable() {
        Category category = new Category("name");
        Parcel parcel = MockParcel.obtain();
        category.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);
        Category createdFromParcel = Category.CREATOR.createFromParcel(parcel);
        Assert.assertThat(createdFromParcel.name, is("name"));
    }
}
