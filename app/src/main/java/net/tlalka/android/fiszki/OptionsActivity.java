package net.tlalka.android.fiszki;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import net.tlalka.android.fwork.FworkActivity;

public class OptionsActivity extends FworkActivity {

    private View view;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle, R.layout.options_view);

        this.view = findViewById(R.id.view);
        this.view.setOnClickListener(new PrivListener(this));
    }

    private class PrivListener implements OnClickListener {
        private Context context;

        public PrivListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            ((OptionsActivity) this.context).finish();
        }
    }
}
