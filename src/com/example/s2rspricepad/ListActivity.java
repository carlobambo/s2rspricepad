package com.example.s2rspricepad;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.ListFragment;
import android.content.res.Configuration;
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
	ItemAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null) {
			return;
		}
		init();
		
	}
	private void init(){
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
				ds = new ItemsDataSource(this);  
				ds.open();
				List<Item> items = ds.getAllItems();
				listFragment = new MyListFragment();
				getFragmentManager().beginTransaction().add(R.id.fragment_list,listFragment).commit();
			 	
				List<Item> listWithHeader = new ArrayList<Item>();
				int count = 0;
				for(Item item : items){
					if(count == 0){
						//create SectionHeader;
						listWithHeader.add(new SectionHeader(item.getCategory()));
					}else if(items.get(count-1).getCategory() != item.getCategory()){
						//create SectionHeader;
						listWithHeader.add(new SectionHeader(item.getCategory()));
					}
					listWithHeader.add(item);
//					adapter.addItem(item);
					count++;
				}
				adapter = new ItemAdapter(this, listWithHeader);
				listFragment.setListAdapter(adapter);
				ds.close();
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
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
        	Toast.makeText(this, "PORTRAIT", Toast.LENGTH_LONG).show();
        	init();
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        	Toast.makeText(this, "LANDSCAPE", Toast.LENGTH_LONG).show();
        	init();
        }

    }
	public class SectionHeader extends Item{
		public SectionHeader(String name){
			this.setItemName(name);
		}
	}
	/**
	 * A placeholder fragment containing a simple view.
	 */
	
}
