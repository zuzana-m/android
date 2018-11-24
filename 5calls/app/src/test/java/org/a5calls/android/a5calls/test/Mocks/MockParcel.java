package org.a5calls.android.a5calls.test.Mocks;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.mockito.stubbing.Answer;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyFloat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockParcel {

    @NonNull
    public static Parcel obtain() {
        return new MockParcel().mParcel;
    }

    private int mPosition = 0;
    private List<Object> mStore = new LinkedList<>();
    private Parcel mParcel = mock(Parcel.class);

    private MockParcel() {
        setupWrites();
        setupReads();
        setupOthers();
    }

    private void setupWrites() {
        final Answer<Object> answer = i -> {
            final Object arg = i.getArgumentAt(0,Object.class);
            mStore.add(arg);
            return arg;
        };
        doAnswer(answer).when(mParcel).writeByte(anyByte());
        doAnswer(answer).when(mParcel).writeInt(anyInt());
        doAnswer(answer).when(mParcel).writeString(anyString());
        doAnswer(answer).when(mParcel).writeParcelable(any(Parcelable.class), anyInt());
        doAnswer(answer).when(mParcel).writeLong(anyLong());
        doAnswer(answer).when(mParcel).writeFloat(anyFloat());
        doAnswer(answer).when(mParcel).writeDouble(anyDouble());
        doAnswer(answer).when(mParcel).writeTypedArray(any(), anyInt());
        doAnswer(answer).when(mParcel).writeTypedList(any());
    }

    private void setupReads() {
        final Answer<Object> answer = i -> mStore.get(mPosition++);
        when(mParcel.readByte()).thenAnswer(answer);
        when(mParcel.readInt()).thenAnswer(answer);
        when(mParcel.readString()).thenAnswer(answer);
        when(mParcel.readParcelable(any(ClassLoader.class))).then(answer);
        when(mParcel.readLong()).thenAnswer(answer);
        when(mParcel.readFloat()).thenAnswer(answer);
        when(mParcel.readDouble()).thenAnswer(answer);
        when(mParcel.createTypedArray(any())).thenAnswer(answer);
        when(mParcel.createTypedArrayList(any())).thenAnswer(answer);
    }

    private void setupOthers() {
        doAnswer(i -> {
            mPosition = i.getArgumentAt(0, Integer.TYPE);
            return null;
        }).when(mParcel).setDataPosition(anyInt());
    }

}