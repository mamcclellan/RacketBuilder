package edu.depaul.csc472.m.mcclellan.mcclellanmracketbuilder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListActivity extends android.app.ListActivity {

    private static final int BUILDCODE = 100;
    private final String FILENAME = "RacketProfiles";

    private static ArrayList<Profile> PROFILES;
    private Profile selectedProfile;
    private int idCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idCounter = 0;
        PROFILES = LoadProfiles();
        if (PROFILES.size() == 0)
            PROFILES = getDefaultProfiles();
        idCounter = PROFILES.size();
        setContentView(R.layout.activity_list);
        final ListView listView = getListView();
        setListAdapter(new ProfileAdapter(this));


        // Handle adding new profiles
        ImageView add_icon = (ImageView) findViewById(R.id.button_add_racket);
        add_icon.setOnClickListener(
                new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (PROFILES.size() < 30)
                        {
                            PROFILES.add(0, new Profile("New Racket", idCounter++,
                                    3, 3, 5, 0, 1, 0, 0, 4, 0, 0, 2,
                                    System.currentTimeMillis()));
                            ((ProfileAdapter) getListAdapter()).notifyDataSetChanged();
                        } else {
                            Toast.makeText(ListActivity.this,
                                    "Racket Limit reached", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // Handle deleting racket profiles
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                                   int position, long id) {
                        AlertDialog.Builder adb = new AlertDialog.Builder(ListActivity.this,
                                R.style.AppCompatAlertDialogStyle);
                        adb.setTitle("Delete?");
                        adb.setMessage("Do you want to delete "
                                + PROFILES.get(position).getName() + "?");
                        adb.setNegativeButton("Cancel", null);
                        final int toRemove = position;
                        adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PROFILES.remove(toRemove);
                                idCounter--;
                                ((ProfileAdapter) getListAdapter()).notifyDataSetChanged();
                            }
                        });
                        adb.show();
                        return true;
                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        SaveProfiles();
        super.onStop();
    }

    @Override
    public void finish() {
        SaveProfiles();
        super.finish();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        selectedProfile = PROFILES.get(position);
        Intent intent = new Intent(ListActivity.this, BuildActivity.class);

        intent.putExtra("Profile", (Parcelable) selectedProfile);
        startActivityForResult(intent, BUILDCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BUILDCODE) {
            if (resultCode == RESULT_OK && data != null) {
                selectedProfile.setProfile((Profile) data.getParcelableExtra("Profile"));
                ((ProfileAdapter) getListAdapter()).notifyDataSetChanged();
            }
        }
    }

    // Custom adapter to use for list
    static class ProfileAdapter extends BaseAdapter {

        private LayoutInflater inflater;
        private StringBuilder s;

        ProfileAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // For quicker list view element updates, hopefully
            s = new StringBuilder();
        }

        @Override
        public int getCount() {
            return PROFILES.size();
        }

        @Override
        public Object getItem(int i) {
            return PROFILES.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            View row = convertView;
            if (row == null) {
                row = inflater.inflate(R.layout.activity_list_element, parent, false);
                holder = new ViewHolder();
                holder.profileName = (TextView) row.findViewById(R.id.profile_name);
                holder.shortDesc = (TextView) row.findViewById(R.id.short_description);
                holder.updateTime = (TextView) row.findViewById(R.id.update_time);
                row.setTag(holder);
            } else {
                holder = (ViewHolder) row.getTag();
            }

            Profile profile = PROFILES.get(position);
            holder.profileName.setText(profile.getName());
            // Build short description
            if (profile.getHeadSize() < 3) s.append("Small");
            else if (profile.getHeadSize() < 6) s.append("Average-sized");
            else s.append("Over-sized");
            if (profile.getWeight() < 3) s.append(", lightweight");
            else if (profile.getWeight() < 6) s.append(", average-weighted");
            else s.append(", heavy");
            if (profile.getBalance() < 3) s.append(", head-light");
            else if (profile.getBalance() < 6) s.append(", balanced");
            else s.append(", head-heavy");
            s.append(" racket.");

            holder.shortDesc.setText(s.toString());
            s.setLength(0);

            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.US);
            String update = "Last Update: " +
                    sdf.format(profile.getUpdateTime());
            holder.updateTime.setText(update);

            return row;
        }

        static class ViewHolder {
            TextView profileName;
            TextView shortDesc;
            TextView updateTime;
        }
    }

    // Save the profiles as one giant list
    // Magic - I cannot believe this works
    public void SaveProfiles() {
        FileOutputStream fos;

        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(PROFILES);
            oos.close();
            fos.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load the Array List of saved profiles, if it exists
    private ArrayList<Profile> LoadProfiles() {
        FileInputStream fis;
        ObjectInputStream ois;
        ArrayList<Profile> loaded = new ArrayList<>();

        try {
            fis = openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);
            // I know, a dangerous cast
            loaded = (ArrayList<Profile>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return loaded;
    }

    // If file has not been saved yet, as in the first time
    private ArrayList<Profile> getDefaultProfiles() {
        ArrayList<Profile> defaultList = new ArrayList<>(1);

        Profile balanced = new Profile("Balanced", idCounter++, 3, 3, 5, 0, 1, 0, 0, 4, 0, 0, 2,
                System.currentTimeMillis());

        defaultList.add(balanced);

        return defaultList;
    }


}
