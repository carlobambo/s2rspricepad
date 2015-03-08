package com.example.s2rspricepad;

import java.util.ArrayList;
import java.util.List;

import com.example.s2rspricepad.MyListFragment.Callback;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;



public class CartItemListFragment extends ListFragment {
	List<Item> items;
	private Callback mCallback;
	private CartAdapter adapter;
			
	public CartItemListFragment(Context context) {
		this.adapter = new CartAdapter(context, new ArrayList<CartItem>()); 
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
	
	public void addItem(Item item, int qty){
		adapter.add(new CartItem(item, qty));
		adapter.notifyDataSetChanged();
	}
	class CartItem extends Item{
		int qty;
		public CartItem(Item item, int qty){
			this.setItemName(item.getItemName());
			this.qty = qty;
			this.setPrice(item.getPrice());
		}
		
		public double getTotalPrice(){
			return this.getPrice() * this.qty;
		}
	}
	class CartAdapter extends ArrayAdapter<CartItem>{

		
		public CartAdapter(Context context, List <CartItem> items) {
			super(context, R.layout.fragment_cart_item, items);
			// TODO Auto-generated constructor stub
		}
		
		
		
	}
}
