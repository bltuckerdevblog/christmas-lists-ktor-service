package com.abnormallydriven.christmaslistservice.users

data class User(val id: Long,
                val name: String,
                val isNice : Boolean = true)