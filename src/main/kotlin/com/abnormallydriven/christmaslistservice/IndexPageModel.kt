package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.User

data class IndexPageModel(val users: List<User> = emptyList(),
                          val error: String? = null)