package net.tlalka.android.fiszki.elements;

import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.activities.AbstractActivity;
import net.tlalka.android.fiszki.activities.SettingsActivity;

public enum OptionsElement {

    SETTINGS(R.id.item_settings) {
        @Override
        public boolean action(AbstractActivity activity) {
            activity.startActivity(SettingsActivity.class);
            return true;
        }
    },
    ABOUT(R.id.item_about) {
        @Override
        public boolean action(AbstractActivity activity) {
            activity.alert("Not implemented yet.");
            return true;
        }
    },
    BACK(R.id.item_back) {
        @Override
        public boolean action(AbstractActivity activity) {
            activity.finish();
            return true;
        }
    };

    private final int resourceId;

    OptionsElement(int resourceId) {
        this.resourceId = resourceId;
    }

    public static boolean triggerAction(AbstractActivity activity, int resourceId) {
        for (OptionsElement optionsElement : values()) {
            if (optionsElement.resourceId == resourceId) {
                return optionsElement.action(activity);
            }
        }
        return false;
    }

    public abstract boolean action(AbstractActivity activity);
}
