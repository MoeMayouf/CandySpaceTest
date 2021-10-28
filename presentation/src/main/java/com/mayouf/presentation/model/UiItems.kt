package com.mayouf.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class UiItems(
    val dataBadgeCounts: @RawValue UiBadgeCounts,
    val reputation: @RawValue Int,
    val creationDate: @RawValue Int,
    val userId: @RawValue Int,
    val location:  @RawValue String,
    val profileImage: @RawValue String,
    val displayName:  @RawValue String
) : Parcelable