package hu.vsza.mail2self;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Intent intent = getIntent();
        if (Intent.ACTION_SEND.equals(intent.getAction()) &&
                "text/plain".equals(intent.getType())) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            if (sharedText != null) {
                EditText subject = (EditText)findViewById(R.id.subject);
                subject.setText(sharedText, TextView.BufferType.EDITABLE);
            }
        }
    }

    public void sendMail(View v) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String address = sharedPref.getString(SettingsActivity.KEY_EMAIL_ADDR, "");
        if (address == null || address.equals("")) {
            Toast.makeText(getBaseContext(), R.string.set_mail_addr, Toast.LENGTH_LONG).show();
            return;
        }
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        i.putExtra(Intent.EXTRA_SUBJECT, getTextContents(R.id.subject));
        i.putExtra(Intent.EXTRA_TEXT, getTextContents(R.id.message));
        try {
            startActivity(Intent.createChooser(i, getString(R.string.send_mail)));
        } catch (Exception ex) {
            Toast.makeText(this, R.string.no_mail_clients, Toast.LENGTH_LONG).show();
        }
    }

    private String getTextContents(int id) {
        EditText et = (EditText)findViewById(id);
        return et.getText().toString();
    }

    public void showSettings(View v) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}
