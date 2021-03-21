package com.example.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class WordViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {
    private Application mApplication;
    private boolean mCompleteFlag;

    /**
     * Creates a {@code AndroidViewModelFactory}
     *
     * @param application an application to pass in {@link AndroidViewModel}
     */
    public WordViewModelFactory(@NonNull Application application, boolean completeFlag) {
        super(application);
        mApplication = application;
        mCompleteFlag = completeFlag;
    }

    @Override
    public <T extends ViewModel> T	create(Class<T> modelClass) {
        return (T) new WordViewModel(mApplication, mCompleteFlag);
    }
}
