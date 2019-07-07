package com.skyline.app.skylinetrackercommands;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterListCars  extends RecyclerView.Adapter <AdapterListCars.PendingViewHolder>  {


    Context mContext;
    List<String> mNameDB;
    private OnItemClickListener mListener;

    public AdapterListCars(Context context, List<String> NameDB) {
        mContext = context;
        mNameDB = NameDB;
    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_list, viewGroup, false);
        return new PendingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder pendingViewHolder, int i) {

        pendingViewHolder.carName.setText(mNameDB.get(i));

    }

    @Override
    public int getItemCount() {
        return mNameDB.size();
    }

    public class PendingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {


        TextView carName;



        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.carName);


            itemView.setOnClickListener(this);
            // itemView.setOnCreateContextMenuListener(this);

        }

        //        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//            if (mListener != null){
//                int position = getAdapterPosition();
//
//                if (position != RecyclerView.NO_POSITION){
//                    switch (item.getItemId()){
//                        case 1:
//                            mListener.onJourneyInfoClick(position);
//                            return true;
//
//                        case 2:
//                            mListener.onDeleteClick(position);
//                            return true;
//
//                        case 3:
//                            mListener.onAmendClick(position);
//                            return true;
//                        case 4:
//                            mListener.onAcknowledgeClick(position);
//
//
//                    }
//
//                }
//            }
//            return false;
//        }
//
        @Override
        public void onClick(View v) {

            if (mListener != null){
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }
//
//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//
//            menu.setHeaderTitle("Select Action");
//
//            MenuItem doWhatever = menu.add(Menu.NONE,1,1,"Journey Info");
//            MenuItem delete = menu.add(Menu.NONE,2,2,"Cancel");
//            MenuItem amend = menu.add(Menu.NONE,3,3,"Amend");
//            MenuItem acknowledge = menu.add(Menu.NONE,4,4,"Acknowledge");
//
//
//
//            doWhatever.setOnMenuItemClickListener(this);
//            delete.setOnMenuItemClickListener(this);
//            amend.setOnMenuItemClickListener(this);
//            acknowledge.setOnMenuItemClickListener(this);
//        }
//    }
    }


    public interface OnItemClickListener{
        void onItemClick (int position);

//        void onJourneyInfoClick(int position);
//
//        void onDeleteClick(int position);
//
//        void onAmendClick(int position);
//
//        void onAcknowledgeClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


}
