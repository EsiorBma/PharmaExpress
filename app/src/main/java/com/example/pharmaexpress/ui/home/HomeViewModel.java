package com.example.pharmaexpress.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {


    private final MutableLiveData<String> mWelcomeMessage;
    private final MutableLiveData<String> mNotifications;

    public HomeViewModel() {
        mWelcomeMessage = new MutableLiveData<>();
        mNotifications = new MutableLiveData<>();

        mWelcomeMessage.setValue("Welcome to PharmaExpress!");
        mNotifications.setValue("New pharmacies and promotions!");
    }

    public LiveData<String> getWelcomeMessage() {
        return mWelcomeMessage;
    }

    public LiveData<String> getNotifications() {
        return mNotifications;
    }
}