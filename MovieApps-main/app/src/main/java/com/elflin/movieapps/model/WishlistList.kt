package com.elflin.movieapps.model

import com.google.gson.annotations.SerializedName

data class WishlistList(@SerializedName("wishlists") val wishlistListS: List<Wishlist>)
