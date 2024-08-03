package org.moviedb.kmp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val id:String,
    val prevKey:Int?,
    val nextKey:Int?,
    val type:Int
)

const val REMOTE_KEY_UPCOMING = 1
const val REMOTE_KEY_TV_TRENDING = 2
const val REMOTE_KEY_PEOPLE = 3
const val REMOTE_KEY_DISCOVERY= 4
