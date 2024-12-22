package com.example.todo

import android.icu.text.CaseMap.Title
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.Date

@Entity //talks to DAO
data class Todo(
    @PrimaryKey(autoGenerate = true) //key is generated automatically with the input
    var id:Int=0,
    var title: String,
    var body:String,
    var createdAt: Date
)


