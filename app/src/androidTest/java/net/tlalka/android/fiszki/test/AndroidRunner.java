package net.tlalka.android.fiszki.test;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

public class AndroidRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader classLoader, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(classLoader, TestFiszki.class.getName(), context);
    }
}