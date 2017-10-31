package com.example.swamy.recyclerviewdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] firstNameList = {"Albert", "Paul", "Linus", "Neils", "Hargobind",
    "Issac", "Chandrasekhara", "Leonhard", "Johann Carl"};
    private String[] lastNameList = {"Einstein", "Dirac", "Pauling", "Bohr", "Khorana",
    "Newton", "Raman", "Euler", "Gauss"};

    private int changeOrient = 0; //not used here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        //horizontal scroll
      //  mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(firstNameList, lastNameList);
        mRecyclerView.setAdapter(mAdapter);


    }



    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ListRowEntry> {

        private String[] fName;
        private String[] lName;

        //init the adapter data source in the constructor
        public MyAdapter(String [] firstName, String [] lastName)
        {
            fName = firstName;
            lName = lastName;
        }

        //init the views in the viewholder using findviewbyid
        public class ListRowEntry extends RecyclerView.ViewHolder
        {

            public TextView mFname;
            public TextView mLname;

            public ListRowEntry(ViewGroup v)
            {
                super(v);

                mFname = (TextView) v.findViewById(R.id.fname);
                mLname = (TextView) v.findViewById(R.id.lname);

            }
        }

        // Create new views (invoked by the layout manager)
        @Override
        public MyAdapter.ListRowEntry onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listrowentry, parent, false);

            ListRowEntry vh = new ListRowEntry(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyAdapter.ListRowEntry holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element

            holder.mFname.setText(fName[position]);
            holder.mFname.setTextColor(Color.GREEN);
            holder.mLname.setText(lName[position]);

        }

        @Override
        public int getItemCount()
        {

            return fName.length;
        }

        @Override
        public int getItemViewType(int position)
        {
        return 0;
        }
    }



    }
