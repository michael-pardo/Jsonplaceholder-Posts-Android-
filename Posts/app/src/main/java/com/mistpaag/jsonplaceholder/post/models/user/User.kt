package com.mistpaag.jsonplaceholder.post.models.user

import com.mistpaag.jsonplaceholder.post.models.user.Address
import com.mistpaag.jsonplaceholder.post.models.user.Company

data class User(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)