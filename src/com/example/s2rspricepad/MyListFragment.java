package com.example.s2rspricepad;

import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class MyListFragment extends ListFragment {
	private ItemsDataSource ds;
	List<Item> items;
	private Callback mCallback;
			
	public MyListFragment() {

	}
	
	public interface Callback{
		public void onItemSelected(Item item);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callback)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }
        mCallback = (Callback) activity;
    }
	
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        if(this.getListAdapter().getItem(position) instanceof ItemAdapter.SectionHeader){
        	return;
        }
        mCallback.onItemSelected((Item)this.getListAdapter().getItem(position));
    }
}
