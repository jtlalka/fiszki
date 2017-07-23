package net.tlalka.android.fiszki.view.activities;

import javax.inject.Inject;

import android.os.Bundle;
import android.widget.ExpandableListView;

import butterknife.BindView;
import net.tlalka.android.fiszki.R;
import net.tlalka.android.fiszki.domain.controllers.ListController;
import net.tlalka.android.fiszki.view.adapters.WordsAdapter;

public class WordsActivity extends BasePageActivity {

    @BindView(R.id.word_list_view)
    protected ExpandableListView listView;

    @Inject
    protected ListController listController;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.words_activity);
        super.getActivityComponent().inject(this);

        listView.setAdapter(new WordsAdapter(this, listController));
    }
}
