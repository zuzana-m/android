package org.a5calls.android.a5calls.test;

import android.os.Parcel;

import org.a5calls.android.a5calls.test.Mocks.MockParcel;
import org.a5calls.android.a5calls.model.Category;
import org.a5calls.android.a5calls.model.Contact;
import org.a5calls.android.a5calls.model.FieldOffice;
import org.a5calls.android.a5calls.model.Issue;
import org.a5calls.android.a5calls.model.Outcome;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class IssueModelTest {

    @Test
    public void testIssueIsParcelable() {
        FieldOffice[] fieldOffices = new FieldOffice[] {new FieldOffice("city","phone")};
        Contact contact = new Contact("id","name","phone","photoURL", "party",
                "state", "reason", "area", fieldOffices);
        List<Outcome> outcomesList = new ArrayList<>();
        outcomesList.add(new Outcome(Outcome.Status.CONTACT));
        Category[] categories = new Category[] {new Category("name")};

        Contact[] contacts = new Contact[] {contact};
        Issue issue = new Issue("id", "name", "reason", "script", true,
                "link", "linkTitle", contacts, outcomesList, categories);

        Parcel parcel = MockParcel.obtain();
        issue.writeToParcel(parcel, 0);

        Issue createdFromParcel = Issue.CREATOR.createFromParcel(parcel);

        Assert.assertThat(createdFromParcel.reason, is("reason"));
        Assert.assertThat(createdFromParcel.id, is("id"));
        Assert.assertThat(createdFromParcel.link, is("link"));
        Assert.assertThat(createdFromParcel.linkTitle, is("linkTitle"));
        Assert.assertThat(createdFromParcel.name, is("name"));
        Assert.assertThat(createdFromParcel.script, is("script"));
        Assert.assertThat(createdFromParcel.inactive, is(true));
        Assert.assertThat(createdFromParcel.categories[0].name, is(categories[0].name));
        Assert.assertThat(createdFromParcel.outcomeModels.get(0).label, is(outcomesList.get(0).label));
        Assert.assertThat(createdFromParcel.contacts[0].id, is(contacts[0].id));
        Assert.assertThat(createdFromParcel.contacts[0].field_offices[0].phone, is(contact.field_offices[0].phone));
    }

}
