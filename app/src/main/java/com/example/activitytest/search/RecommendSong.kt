package com.example.activitytest.search

data class RecommendSong(
    val code: Int,
    val songs: List<SongR>
)

data class SongR(
    val albumR: AlbumR,
    val alg: String,
    val alias: List<Any>,
    val artists: List<ArtistXX>,
    val audition: Any,
    val bMusic: BMusic,
    val commentThreadId: String,
    val copyFrom: String,
    val copyrightId: Int,
    val crbt: Any,
    val dayPlays: Int,
    val disc: String,
    val duration: Int,
    val fee: Int,
    val ftype: Int,
    val hMusic: HMusic,
    val hearTime: Int,
    val id: Int,
    val lMusic: LMusic,
    val mMusic: MMusic,
    val mark: Int,
    val mp3Url: String,
    val mvid: Int,
    val name: String,
    val no: Int,
    val noCopyrightRcmd: Any,
    val originCoverType: Int,
    val originSongSimpleData: Any,
    val playedNum: Int,
    val popularity: Double,
    val position: Int,
    val privilege: Privilege,
    val recommendReason: String,
    val ringtone: Any,
    val rtUrl: Any,
    val rtUrls: Any,
    val rtype: Int,
    val rurl: Any,
    val score: Int,
    val starred: Boolean,
    val starredNum: Int,
    val status: Int
)

data class AlbumR(
    val alias: List<Any>,
    val artistR: ArtistR,
    val artists: List<ArtistXR>,
    val blurPicUrl: String,
    val briefDesc: String,
    val commentThreadId: String,
    val company: String,
    val companyId: Int,
    val copyrightId: Int,
    val description: String,
    val id: String,//Int,
    val mark: Int,
    val name: String,
    val onSale: Boolean,
    val paid: Boolean,
    val pic: Long,
    val picId: Long,
    val picId_str: String,
    val picUrl: String,
    val publishTime: Long,
    val size: Int,
    val songs: List<Any>,
    val status: Int,
    val subType: Any,
    val tags: String,
    val type: String
)

data class ArtistXX(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val followed: Boolean,
    val id:String,//Int,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String
)

data class BMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: String, //int
    val name: String,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Double
)

data class HMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: String, //Int,
    val name: String,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Double
)

data class LMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: String, //Int,
    val name: String,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Double
)

data class MMusic(
    val bitrate: Int,
    val dfsId: Int,
    val extension: String,
    val id: String,//Int,
    val name: String,
    val playTime: Int,
    val size: Int,
    val sr: Int,
    val volumeDelta: Double
)

data class Privilege(
    val chargeInfoList: List<ChargeInfo>,
    val cp: Int,
    val cs: Boolean,
    val dl: Int,
    val downloadMaxbr: Int,
    val fee: Int,
    val fl: Int,
    val flag: Int,
    val freeTrialPrivilege: FreeTrialPrivilege,
    val id: String,//Int,
    val maxbr: Int,
    val payed: Int,
    val pl: Int,
    val playMaxbr: Int,
    val preSell: Boolean,
    val rscl: Any,
    val sp: Int,
    val st: Int,
    val subp: Int,
    val toast: Boolean
)

data class ArtistR(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val followed: Boolean,
    val id: String,//Int,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String
)

data class ArtistXR(
    val albumSize: Int,
    val alias: List<Any>,
    val briefDesc: String,
    val followed: Boolean,
    val id: String,//Int,
    val img1v1Id: Long,
    val img1v1Id_str: String,
    val img1v1Url: String,
    val musicSize: Int,
    val name: String,
    val picId: Int,
    val picUrl: String,
    val topicPerson: Int,
    val trans: String
)

data class ChargeInfo(
    val chargeMessage: Any,
    val chargeType: Int,
    val chargeUrl: Any,
    val rate: Int
)

data class FreeTrialPrivilege(
    val resConsumable: Boolean,
    val userConsumable: Boolean
)