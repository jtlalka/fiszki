package net.tlalka.android.fiszki.view.activities

import javax.inject.Inject

import android.os.Bundle
import android.widget.ExpandableListView

import butterknife.BindView
import net.tlalka.android.fiszki.R
import net.tlalka.android.fiszki.domain.controllers.ListController
import net.tlalka.android.fiszki.view.adapters.WordsAdapter

class WordsActivity : BasePageActivity() {

    @BindView(R.id.word_list_view)
    lateinit var listView: ExpandableListView

    @Inject
    lateinit var listController: ListController

    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        super.setContentView(R.layout.words_activity)
        super.activityComponent.inject(this)

        listView.setAdapter(WordsAdapter(this, listController))
    }
}
