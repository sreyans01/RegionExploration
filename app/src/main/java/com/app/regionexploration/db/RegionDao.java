package com.app.regionexploration.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.regionexploration.model.Region;

import java.util.List;

@Dao
public interface RegionDao {

    @Query("SELECT * FROM Region")
    List<Region> getAllRegions();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllRegions(List<Region> regions);

    @Insert
    void insertRegion(Region... region);

    @Delete
    void delete(Region user);
}