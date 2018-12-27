package com.abnormallydriven.christmaslistservice.wishlists

import com.abnormallydriven.christmaslistservice.users.UserDao
import java.util.concurrent.atomic.AtomicLong

class WishListDao(private val userDao: UserDao) {

    private val wishlistIdSequence = AtomicLong(1)
    private val wishlistStore = mutableMapOf<Long, WishList>()

    fun createWishList(wishlistName: String, userId: Long): WishList {

        val userById = userDao.getUserById(userId) ?: throw RuntimeException("User must exist before creating wishlist")

        val wishList = WishList(wishlistIdSequence.getAndIncrement(),
                userById.id,
                wishlistName)

        wishlistStore[wishList.id] = wishList

        return wishList
    }

    fun getWishListById(id: Long): WishList? {
        return wishlistStore[id]
    }

    fun createChristmasWishList(userId: Long): WishList {
        return createWishList("Christmas List", userId)
    }

    fun getChristmasWishList(userId: Long): WishList?{
        return wishlistStore.values
                .toList()
                .firstOrNull { list: WishList -> list.userId == userId && list.name == "Christmas List"}
    }

    fun addItem(id: Long, itemName: String) {
        wishlistStore[id]?.addItem(WishListItem(itemName)) ?: throw RuntimeException("Invalid wishlist id")
    }

    fun removeItem(id: Long, itemName: String) {
        val wishList = wishlistStore[id] ?: throw RuntimeException("Invalid wishlist id")

        wishList.removeItem(WishListItem(itemName))

    }

    fun getUserWishLists(userId: Long): List<WishList> {
        return wishlistStore.filterKeys {
            it == userId
        }
        .values
        .toList()
    }

}