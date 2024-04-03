package com.example.api2

data class UserResponseListModel(
    val entity:String?=null,
    val count:String?=null,
    val items:List<Item>?=null

)
data class Item(
    val id:String?=null,
    val name:String?=null,
    val email:String?=null,
    val password:String?=null,
    val contact:String?=null
)

