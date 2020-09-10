package com.mistpaag.jsonplaceholder.post.models.user

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mistpaag.jsonplaceholder.post.models.user.Address
import com.mistpaag.jsonplaceholder.post.models.user.Company
@Entity
data class User(
    @Embedded
    val address: Address,
    @Embedded(prefix = ("com_"))
    val company: Company,
    val email: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)