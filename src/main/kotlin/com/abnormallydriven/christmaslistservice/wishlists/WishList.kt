package com.abnormallydriven.christmaslistservice.wishlists

class WishList(val id: Long,
               val userId: Long,
               val name: String) {

    private val items: MutableList<WishListItem> = mutableListOf()


    fun addItem(item: WishListItem) {
        items.add(item)
    }

    fun removeItem(item: WishListItem) {
        items.remove(item)
    }

    fun getItems(): List<WishListItem> {
        return items
    }

}