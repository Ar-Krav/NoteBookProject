package ark.notebookproject;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NotesListActivity extends AppCompatActivity {

    LinearLayout noteListLinearLayout;

    MenuItem deleteNoteMenuItem;
    Button createNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        createNoteBtn = (Button) findViewById(R.id.createNoteBtn);
        createNoteBtn.setOnClickListener(getCreateNoteBtnListener());

        noteListLinearLayout = (LinearLayout) findViewById(R.id.noteLisLL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        deleteNoteMenuItem = menu.findItem(R.id.deleteNoteMI);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        deleteNoteMenuItem.setVisible(true);
        super.onCreateContextMenu(menu, v, menuInfo);
    }



    private View.OnClickListener getCreateNoteBtnListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,250);
                    layoutParams.topMargin = 15;

                TextView createdNote = new TextView(NotesListActivity.this);
                    createdNote.setText("New Note!");
                    createdNote.setTextSize(26);
                    createdNote.setBackgroundResource(R.drawable.notes_border);

                    registerForContextMenu(createdNote);

                noteListLinearLayout.addView(createdNote,layoutParams);
            }
        };
    }
}
