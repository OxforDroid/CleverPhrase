package su.keke.cleverphrase;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Выбрана случайная фраза!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                bildRandom(textView);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {

            bildGum(textView);
        } else if (id == R.id.nav_gallery) {
            bildTex(textView);


        } else if (id == R.id.nav_slideshow) {
            bildRandom(textView);
        } else if (id == R.id.nav_manage) {


        } else if (id == R.id.nav_share) {
            share(textView);
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void share (TextView view)
    {
        String str = textView.getText().toString();
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Я думаю что ");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, str);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void bildGum (View view){

        String[]gum_list = getResources().getStringArray(R.array.gum_list);
// Вычисляем, сколько слов в каждом списке
        int gumLength = gum_list.length;
//Генерируем случайное число
        int randl = (int) (Math.random() * gumLength);
//Теперь строим фразу
        String phrase =  gum_list[randl];
        textView.setText("");//очищаем поле
        textView.setText(phrase);//вставляем фразу
    }

    public void bildTex (View v){
        String[]tex_list = getResources().getStringArray(R.array.tex_list);
        int texLength = tex_list.length;
        int rand3 = (int) (Math.random() * texLength);
        String phrase2 =  tex_list[rand3];

        textView.setText("");
        textView.setText(phrase2);

    }

    public void bildRandom (View v){
        String[]all_list = getResources().getStringArray(R.array.all_list);
        int allLength = all_list.length;
        int rand4 = (int) (Math.random() * allLength);
        String phrase4 =  all_list[rand4];

        textView.setText("");
        textView.setText(phrase4);

    }
}
