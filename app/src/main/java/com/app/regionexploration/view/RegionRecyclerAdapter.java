package com.app.regionexploration.view;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.app.regionexploration.R;
import com.app.regionexploration.model.Region;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.ArrayList;
import java.util.List;

public class RegionRecyclerAdapter extends RecyclerView.Adapter<RegionRecyclerAdapter.RegionViewHolder>{

    private Context context;
    private List<Region> regionList = new ArrayList<>();

    public RegionRecyclerAdapter(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_region,parent,false);
        return new RegionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionRecyclerAdapter.RegionViewHolder holder, int position) {

        holder.nameTV.setText(regionList.get(position).getName());
        holder.capitalTV.setText(regionList.get(position).getCapital());
        holder.regionTV.setText(regionList.get(position).getRegion());
        holder.subregionTV.setText(regionList.get(position).getSubRegion());
        holder.populationTV.setText(String.valueOf(regionList.get(position).getPopulation()));
        StringBuilder borders = new StringBuilder("");
        List<String> bordersList = regionList.get(position).getBorders();
        for(int i=0;i<bordersList.size();i++){
            borders.append(bordersList.get(i));
            if(i!=bordersList.size()-1)
                borders.append(", ");
        }
        holder.bordersTV.setText(borders.toString());
        List<String> languageList = regionList.get(position).getLanguages();
        StringBuilder languages = new StringBuilder("");
        for(int i=0;i<languageList.size();i++){
            languages.append(languageList.get(i));
            if(i!=languageList.size()-1)
                languages.append(", ");
        }
        holder.languageTV.setText(languages.toString());
        //holder.nameTV.setText(regionList.get(position).getName());

        try{
            String flagUrl = regionList.get(position).getFlag();
            //GlideToVectorYou.init().with(context).load(Uri.parse(flagUrl),holder.flagIV);
            GlideToVectorYou.justLoadImageAsBackground((Activity)context, Uri.parse(flagUrl), holder.flagIV);

        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        return regionList.size();
    }

    public class RegionViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTV, capitalTV, regionTV, subregionTV, populationTV, bordersTV, languageTV;
        private ImageView flagIV;

        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTV = itemView.findViewById(R.id.nameTV);
            capitalTV = itemView.findViewById(R.id.capitalTV);
            regionTV = itemView.findViewById(R.id.regionTV);
            subregionTV = itemView.findViewById(R.id.subRegionTV);
            populationTV = itemView.findViewById(R.id.populationTV);
            bordersTV = itemView.findViewById(R.id.bordersTV);
            languageTV = itemView.findViewById(R.id.languageTV);
            flagIV = itemView.findViewById(R.id.flagIV2);

        }
    }

    public void updateRegions(List<Region> regions){
        regionList = regions;
        notifyDataSetChanged();
    }

}
