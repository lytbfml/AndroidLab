package com.hw3.geoquiz;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yangxiao on 10/16/2017.
 */

public class ChoicesFragment extends Fragment {
	
	public static final String TAG = "ChoicesFragment";
	
	public ArrayList<String> choices = new ArrayList<>();
	
	private static final String ARG_POSITION = "argument-position";
	
	private int pos = 0;
	
	private OnChoiceSelectListener mListener;
	
	private ChoicesAdaptor choiceAdaptor;
	
	public ChoicesFragment(){
		
	}
	
	public static ChoicesFragment newInstance(int id) {
		ChoicesFragment fragment = new ChoicesFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_POSITION, id);
		fragment.setArguments(args);
		return fragment;
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getArguments() != null) {
			pos = getArguments().getInt(ARG_POSITION);
			choices = new ArrayList<>(Arrays.asList(Questions.ITEMS.get(pos).choices));
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_choices, container, false);
		
		// Set the adapter
		if (view instanceof GridView) {
			Context context = view.getContext();
			GridView gridView = (GridView) view;
			choiceAdaptor = new ChoicesAdaptor(view.getContext(), R.layout.fragment_choices_item, this.choices);
			gridView.setAdapter(choiceAdaptor);
			
			gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// Get the GridView selected/clicked item text
					String selectedItem = parent.getItemAtPosition(position).toString();
					mListener.onChoiceSelectListener(pos, position);
				}
			});
		}
		return view;
	}
	
	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		if (context instanceof ChoicesFragment.OnChoiceSelectListener) {
			mListener = (ChoicesFragment.OnChoiceSelectListener) context;
		} else {
			throw new RuntimeException(context.toString()
					+ " must implement OnListFragmentInteractionListener");
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}
	
	
//	@Override
//	public void onGridItemClick(GridView l, View v, int position, long id) {
//		super.onListItemClick(l, v, position, id);
//		mListener.onChoiceSelectListener(pos, position);
//	}
	
	public void updateView(int id) {
		pos = id;
		choices = new ArrayList<>(Arrays.asList(Questions.ITEMS.get(id).choices));
		choiceAdaptor = new ChoicesAdaptor(getContext(), R.layout.fragment_choices_item, this.choices);
		((GridView)getView()).setAdapter(choiceAdaptor);
	}
	
	
	public interface OnChoiceSelectListener {
		void onChoiceSelectListener(int id , int pos);
	}
}
