package com.studio.domain.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.studio.domain.utils.GsonManager.SimpleGsonType
import timber.log.Timber
import java.lang.reflect.Type

object GsonManager {
    const val SimpleGsonType = "SimpleGsonType"
    private val gsonMap by lazy { mutableMapOf<String, Gson>() }

    init {
        gsonMap[SimpleGsonType] =
            GsonBuilder()
                .setLenient()
                .serializeSpecialFloatingPointValues()
                .create()
    }

    @JvmStatic
    fun createGson(
        key: String,
        gson: Gson
    ) {
        gsonMap[key] = gson
    }

    @JvmStatic
    inline fun <reified T> fromJson(data: String): T? = fromJson(data, getTypeToken<T>())

    @JvmStatic
    fun <T> fromJson(
        data: String,
        type: Type
    ): T? = fromJson(data, type, SimpleGsonType)

    @JvmStatic
    fun <T> fromJson(
        data: String,
        type: Type,
        gsonType: String = SimpleGsonType
    ) = try {
        gsonMap[gsonType]?.fromJson<T>(data, type)
    } catch (e: Exception) {
        Timber.e("Error fromJson: $e")
        null
    }

    @JvmStatic
    inline fun <reified T> fromJson(
        data: String,
        gsonType: String
    ): T? = fromJson(data, getTypeToken<T>(), gsonType)

    @JvmStatic
    fun <T> fromJson(
        data: String,
        classOfType: Class<T>
    ) = fromJson(data, classOfType, SimpleGsonType)

    @JvmStatic
    fun <T> fromJson(
        data: String,
        classOfType: Class<T>,
        gsonType: String
    ) = try {
        gsonMap[gsonType]?.fromJson(data, classOfType)
    } catch (e: Exception) {
        Timber.e("Error fromJson: $e")
        null
    }

    @JvmStatic
    fun <T> toJson(data: T): String = toJson(data, SimpleGsonType)

    @JvmStatic
    fun <T> toJson(
        data: T,
        gsonType: String = SimpleGsonType
    ): String =
        try {
            gsonMap[gsonType]?.toJson(data).orEmpty()
        } catch (e: Exception) {
            Timber.e("Error toJson: $e")
            ""
        }
}

inline fun <reified T> fromJson(
    data: String,
    type: Type = getTypeToken<T>(),
    gsonType: String = SimpleGsonType
): T? = GsonManager.fromJson<T>(data, type, gsonType)

inline fun <reified T> fromJson(
    data: String,
    gsonType: String = SimpleGsonType
): T? = GsonManager.fromJson(data, T::class.java, gsonType = gsonType)

inline fun <reified T> getTypeToken(): Type = object : TypeToken<T>() {}.type
