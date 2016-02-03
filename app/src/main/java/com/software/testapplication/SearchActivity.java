package com.software.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import com.parse.ParseException;

public class SearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        ParseObject.registerSubclass(Employee.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void search(View button) {
        final EditText employeeId = (EditText) findViewById(R.id.EditTextId);
        String employmentId = employeeId.getText().toString();

        ParseQuery<Employee> query = new ParseQuery<Employee>("Employee");
        query.whereEqualTo("EmployeeId", employmentId);
        query.getFirstInBackground(new GetCallback<Employee>() {

            public void done(Employee employee, ParseException e) {
                if (employee != null) {
                    TextView textView = (TextView) findViewById(R.id.TextViewContent);
                    String desc = employee.getString("Name") + "\n" + employee.getString("Phone") + "\n" +
                            employee.getString("Email") + "\n" + employee.getString("Address") + "\n";
                    textView.setText(desc);
                }
            }
        });
    }

    public void addRecord(View button) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}