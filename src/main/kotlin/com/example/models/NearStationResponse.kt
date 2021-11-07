package com.example.models

import com.google.gson.annotations.SerializedName

data class NearStationResponse(
	@SerializedName("pagination") val pagination: Pagination,
	@SerializedName("stations") val stations: List<StationsItem>
)

data class StationsItem(
	@SerializedName("popular_title") val popularTitle: String,
	@SerializedName("short_title") val shortTitle: String,
	@SerializedName("code") val code: String,
	@SerializedName("station_type_name") val stationTypeName: String,
	@SerializedName("distance") val distance: Double,
	@SerializedName("lng") val lng: Double,
	@SerializedName("transport_type") val transportType: String,
	@SerializedName("type_choices") val typeChoices: TypeChoices,
	@SerializedName("station_type") val stationType: String,
	@SerializedName("title") val title: String,
	@SerializedName("type") val type: String,
	@SerializedName("lat") val lat: Double
)

data class Pagination(
	@SerializedName("total") val total: Int,
	@SerializedName("offset") val offset: Int,
	@SerializedName("limit") val limit: Int
)

data class TypeChoices(
	@SerializedName("schedule") val schedule: Schedule
)

data class Schedule(
	@SerializedName("desktop_url") val desktopUrl: String,
	@SerializedName("touch_url") val touchUrl: String
)