package com.moviedb.more.domain.model

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.moviedb.more.R

/**
 * Created by Anil Gudigar on 22,February,2021
 */
data class MoreModel(
    val name: String,
    @DrawableRes val drawableImage: Int,
    val subName: String?,
    @DrawableRes val drawableSubImage: Int?,
    @ColorRes val colorRes: Int
) {

    companion object {
        fun getMoreList(context: Context): List<MoreModel> {
            val moreList = listOf<MoreModel>(
                MoreModel(
                    context.getString(R.string.discussion),
                    R.drawable.ic_people_black_24dp,
                    null,
                    null,
                    R.color.subtext_white
                ),
                MoreModel(
                    context.getString(R.string.leaderboard),
                    R.drawable.ic_leaderboard_white_24dp,
                    null,
                    null,
                    R.color.subtext_white
                ),
                MoreModel(
                    context.getString(R.string.support),
                    R.drawable.ic_contact_support_white_24dp,
                    null,
                    null,
                    R.color.subtext_white
                ),
                MoreModel(
                    context.getString(R.string.api),
                    R.drawable.ic_api_white_24dp,
                    null,
                    null,
                    R.color.subtext_white
                )
            )
            return moreList
        }
    }
}
