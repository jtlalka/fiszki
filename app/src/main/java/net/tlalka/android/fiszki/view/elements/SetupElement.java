package net.tlalka.android.fiszki.view.elements;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.view.activities.AbstractActivity;
import net.tlalka.android.fiszki.view.activities.SettingsActivity;

public enum SetupElement {

    SETTINGS(R.id.item_settings) {
        @Override
        public void action(AbstractActivity activity) {
            activity.startActivity(SettingsActivity.class);
        }
    },
    ABOUT(R.id.item_about) {
        @Override
        public void action(AbstractActivity activity) {
            activity.alert("Not implemented yet.");
        }
    },
    BACK(R.id.item_back) {
        @Override
        public void action(AbstractActivity activity) {
            activity.finish();
        }
    };

    private final int resourceId;

    SetupElement(int resourceId) {
        this.resourceId = resourceId;
    }

    public static boolean triggerAction(AbstractActivity activity, int resourceId) {
        for (SetupElement setupElement : values()) {
            if (setupElement.resourceId == resourceId) {
                setupElement.action(activity);
                return true;
            }
        }
        return false;
    }

    public abstract void action(AbstractActivity activity);
}
