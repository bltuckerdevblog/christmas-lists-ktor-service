package com.abnormallydriven.christmaslistservice.wishlists

class WishList(val id : Long){

    private val items: MutableList<WishListItem> = MutableList()


    fun addItem(item: WishListItem){
        items.add(item)
    }

    fun removeItem(item: WishListItem){
        items.remove(item)
    }


}