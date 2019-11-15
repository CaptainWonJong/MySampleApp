package com.ctwj.mysampleapp.ui.model

data class ImageListModel(var imageList: ArrayList<ImageInfo>) {

    data class ImageInfo(
        val id: Int,
        val name: String,
        val content: String,
        val image: String
    )
}