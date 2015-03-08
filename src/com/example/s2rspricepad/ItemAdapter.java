package com.example.s2rspricepad;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {
	List<Item> items;
	Context context;
	public ItemAdapter(Context context, List<Item> items) {
        super(context, R.layout.fragment_list, items);
        this.items = items;
    }
	
	public View getView(int position, View convertView, ViewGroup parent){
		TextView itemName;
		TextView price;
		Boolean isSectionHeader = getItem(position) instanceof SectionHeader || getItem(position).getClass() == SectionHeader.class;
		int layoutID = isSectionHeader? R.layout.section_header: R.layout.fragment_list;
//		if(convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(layoutID, parent, false);
//			convertView.setTag(viewHolder);	
//		}
		Item item = getItem(position);
		itemName = (TextView) convertView.findViewById(R.id.itemName);
		itemName.setText(item.getItemName());
		
		if(!isSectionHeader){
			price = (TextView) convertView.findViewById(R.id.price);
			price.setText(""+item.getPrice());
		}
		return convertView;
	}
	public void addItem(Item item){
		int count = this.getCount();
		if(this.getCount() < 1){
			//add a section
			createSection(item.getCategory());
		}else if(this.getItem(this.getCount()-1).getCategory() != item.getCategory()){
			createSection(item.getCategory());
		}
		this.add(item);
		this.notifyDataSetChanged();
	}
	public void createSection(String name){
		this.add(new SectionHeader(name));
	}
	class SectionHeader extends Item{
		public SectionHeader(String name){
			this.setItemName(name);
		}
	}
		
}
