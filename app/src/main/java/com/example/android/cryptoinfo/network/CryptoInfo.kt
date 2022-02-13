package com.example.android.cryptoinfo.network

import com.squareup.moshi.Json

data class CryptoInfo(
//    val id: String,
//    @Json(name = "img_src") val imgSrcUrl: String
    val status: String?,
    val data: Data
)

data class Data (
    val stats: Stats,
    val coins: List<Coins>
    )

data class Stats (
    val total: Int?,
    val totalCoins: Int?,
    val totalMarkets: Int?,
    val totalExchanges: Int?,
    val totalMarketCap: String?,
    val total24hVolume: String?
)

data class Coins (
    val uuid: String?,
    val symbol: String?,
    val name: String?,
    val color: String?,
    val iconUrl: String?,
    val marketCap: String?,
    val price: String?,
    val listedAt: Int?,
    val tier: Int?,
    val change: String?,
    val rank: Int?,
    val sparkline: List<String>,
    val lowVolume: Boolean?,
    val coinrankingUrl: String?,
    @Json(name = "24hVolume") val volume: String?,
    val btcPrice: String?
)