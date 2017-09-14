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
import android.widget.Toast;

import java.util.ArrayList;

public class NotesListActivity extends AppCompatActivity {

    LinearLayout noteListLinearLayout;

    MenuItem deleteNoteMenuItem;
    Button createNoteBtn;

    ArrayList<TextView> listOfNotes;
    int idOfSelectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        listOfNotes = new ArrayList<>();

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
    public boolean onOptionsItemSelected(MenuItem item) {
        noteListLinearLayout.removeView(findViewById(idOfSelectedNote));
        Toast.makeText(this, "Delete Item #" + idOfSelectedNote, Toast.LENGTH_SHORT).show();

        listOfNotes.remove(idOfSelectedNote);
        for (int i = idOfSelectedNote; i < listOfNotes.size(); i++){
            TextView bufferedTextView = listOfNotes.get(i);
            bufferedTextView.setId(i);
        }

        deleteNoteMenuItem.setVisible(false);

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        idOfSelectedNote = v.getId();
        deleteNoteMenuItem.setVisible(true);
        super.onCreateContextMenu(menu, v, menuInfo);
    }



    private View.OnClickListener getCreateNoteBtnListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,250);
                    layoutParams.setMargins(15,15,15,15);

                TextView createdNote = new TextView(NotesListActivity.this);
                    createdNote.setId(listOfNotes.size());
                    createdNote.setText("New Note! " + listOfNotes.size());
                    createdNote.setTextSize(26);
                    createdNote.setBackgroundResource(R.drawable.notes_border);

                listOfNotes.add(createdNote);
                registerForContextMenu(createdNote);
                noteListLinearLayout.addView(createdNote,layoutParams);
            }
        };
    }
}
