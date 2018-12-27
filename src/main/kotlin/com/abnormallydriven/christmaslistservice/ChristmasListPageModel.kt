package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.User
import com.abnormallydriven.christmaslistservice.wishlists.WishList

data class ChristmasListPageModel(val user: User,
                                  val christmasList: WishList)