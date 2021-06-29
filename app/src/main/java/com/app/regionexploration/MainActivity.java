package com.app.regionexploration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.regionexploration.db.AppDatabase;
import com.app.regionexploration.model.Language;
import com.app.regionexploration.model.Region;
import com.app.regionexploration.presenter.GetRegionData;
import com.app.regionexploration.presenter.RegionDataCallback;
import com.app.regionexploration.view.RegionRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {
    private Context context = MainActivity.this;
    private RecyclerView regionsRecycler;
    RegionRecyclerAdapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        showProgressDialog();
        regionsRecycler = findViewById(R.id.regionsRecycler);
        regionsRecycler.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration itemDecor = new DividerItemDecoration(context, VERTICAL);
        regionsRecycler.addItemDecoration(itemDecor);
        adapter = new RegionRecyclerAdapter(context);
        regionsRecycler.setAdapter(adapter);
        List<Region> regionList = loadRegionList();
        if(!regionList.isEmpty()){
            updateRecyclerView(regionList);
        }
        loadData(new GetResponseCallback() {
            @Override
            public void OnGettingResponse(List<Region> regions) {

                saveData(regions);
                updateRecyclerView(regions);

            }
        });

    }

    private void updateRecyclerView(List<Region> regionList) {

        dismissProgressDialog();
        if(adapter!=null){
            adapter.updateRegions(regionList);
        }else{
            adapter = new RegionRecyclerAdapter(context);
            adapter.updateRegions(regionList);
        }
    }

    public interface GetResponseCallback{
        void OnGettingResponse(List<Region> regions);
    }
    public void loadData(GetResponseCallback getResponseCallback){

        GetRegionData getRegionData = new GetRegionData(new RegionDataCallback() {
            @Override
            public void OnGettingRegionData(JSONArray jsonArray) {
                List<Region> regionList = new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Region region = new Region();
                        region.setName(jsonObject.getString("name"));
                        region.setCapital(jsonObject.getString("capital"));
                        region.setFlag(jsonObject.getString("flag"));
                        region.setRegion(jsonObject.getString("region"));
                        region.setSubRegion(jsonObject.getString("subregion"));
                        region.setPopulation(jsonObject.getLong("population"));
                        List<String> languageList = new ArrayList<>();
                        String lang = String.valueOf(jsonObject.get("languages"));
                        JSONArray langaugeJsonArray = new JSONArray(lang);
                        for(int j=0;j<langaugeJsonArray.length();j++){
                            JSONObject languageJsonObject = langaugeJsonArray.getJSONObject(j);
                            languageList.add(languageJsonObject.getString("name"));
                        }
                        region.setLanguages(languageList);
                        String borders = String.valueOf(jsonObject.get("borders"));
                        List<String> borderList;
                        if(borders.length()!=0) {
                            borderList = Arrays.asList(borders.substring(1, borders.length() - 1).split(","));
                        }else {
                            borderList = new ArrayList<>();
                        }
                        region.setBorders(borderList);
                        regionList.add(region);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                getResponseCallback.OnGettingResponse(regionList);
            }
        });
        getRegionData.execute("https://restcountries.eu/rest/v2/region/asia");
    }


    private void saveData(List<Region> regionList){
        AppDatabase db  = AppDatabase.getDbInstance(this.getApplicationContext());
        db.regionDao().insertAllRegions(regionList);
    }

    private List<Region> loadRegionList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Region> regionsList =db.regionDao().getAllRegions();
        return  regionsList;
    }


    void showProgressDialog(){
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    void dismissProgressDialog(){
        progressDialog.dismiss();
    }
}