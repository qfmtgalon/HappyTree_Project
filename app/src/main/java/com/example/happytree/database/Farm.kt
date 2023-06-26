package com.example.happytree.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "farmTable")
data class Farm(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "disease")
    val disease: String,
    @ColumnInfo(name = "numberOfTrees")
    val numberOfTrees: Int,
    @ColumnInfo(name = "dateTime")
    val dateTime: String
) : Parcelable