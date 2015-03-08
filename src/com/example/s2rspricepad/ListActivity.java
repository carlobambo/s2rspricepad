package com.example.s2rspricepad;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class ListActivity extends ActionBarActivity implements MyListFragment.Callback{
	MyListFragment listFragment;
	ItemsDataSource ds;
	ActionBarDrawerToggle drawerToggle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.openDrawer(Gravity.LEFT);
		
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
        R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

        	/** Called when a drawer has settled in a completely closed state. */
		    public void onDrawerClosed(View view) {
		        super.onDrawerClosed(view);
		        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		    }
		
		    /** Called when a drawer has settled in a completely open state. */
		    public void onDrawerOpened(View drawerView) {
		        super.onDrawerOpened(drawerView);
		        invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
		    }
		};
		
		if(findViewById(R.id.fragment_list) != null) {
			if(savedInstanceState != null) {
				return;
			}
			ds = new ItemsDataSource(this);  
			ds.open();
			List<Item> items = ds.getAllItems();
			listFragment = new MyListFragment();
			
			getFragmentManager().beginTransaction().add(R.id.fragment_list,listFragment).commit();
			ItemAdapter adapter = new ItemAdapter(this, new ArrayList<Item>());
			listFragment.setListAdapter(adapter);
			for(Item item : items){
				adapter.addItem(item);
			}
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onItemSelected(Item item) {
		// TODO Auto-generated method stub
		Toast.makeText(this, item.getItemName(), Toast.LENGTH_LONG).show();
		
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	
}
