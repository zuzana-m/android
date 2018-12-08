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

public class OutcomeModelTest {

    @Test
    public void testOutcomeIsParcelable() {
        Outcome outcome = new Outcome("label", Outcome.Status.CONTACT);

        Parcel parcel = MockParcel.obtain();
        outcome.writeToParcel(parcel, 0);

        Outcome createdFromParcel = Outcome.CREATOR.createFromParcel(parcel);

        Assert.assertThat(createdFromParcel.label, is(outcome.label));
        Assert.assertThat(createdFromParcel.status, is(outcome.status));
    }

    @Test
    public void testRemovingSkipStatusIssue() {
        FieldOffice[] fieldOffices = new FieldOffice[] {new FieldOffice("city","phone")};
        Contact contact = new Contact("id","name","phone","photoURL", "party",
                "state", "reason", "area", fieldOffices);
        List<Outcome> outcomesList = new ArrayList<>();
        outcomesList.add(new Outcome(Outcome.Status.SKIP));
        outcomesList.add(new Outcome(Outcome.Status.EMPTY));
        outcomesList.add(new Outcome(Outcome.Status.UNAVAILABLE));
        Category[] categories = new Category[] {new Category("name")};

        Contact[] contacts = new Contact[] {contact};
        Issue issue1 = new Issue("id", "name", "reason", "script", true,
                "link", "linkTitle", contacts, outcomesList, categories);

        List<Issue> issues = new ArrayList<>();
        issues.add(issue1);

        List<Issue> filteredIssues = Outcome.filterSkipOutcomes(issues);

        Assert.assertThat(filteredIssues.size(), is(1));
        Assert.assertThat(filteredIssues.get(0).outcomeModels.size(), is(2));

    }

    @Test
    public void testOutcomeStatusFromString() {
        Outcome.Status unsupportedStatus = Outcome.Status.fromString("undef");
        Assert.assertThat(unsupportedStatus, is(Outcome.Status.EMPTY));

        Outcome.Status correctStatus = Outcome.Status.fromString("contact");
        Assert.assertThat(correctStatus, is(Outcome.Status.CONTACT));

    }
}
