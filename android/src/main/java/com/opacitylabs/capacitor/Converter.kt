package com.opacitylabs.capacitor

import com.getcapacitor.JSArray
import com.getcapacitor.JSObject

object Converter {

    fun jsObjectToMap(jsObject: JSObject): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        val keys = jsObject.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            val value = jsObject.get(key)
            map[key] =
                    when (value) {
                        is JSObject -> jsObjectToMap(value)
                        is JSArray -> jsArrayToList(value)
                        else -> value
                    }
        }
        return map
    }

    fun mapToJsObject(map: Map<String, Any>): JSObject {
        val jsObject = JSObject()
        for ((key, value) in map) {
            when (value) {
                is Map<*, *> -> jsObject.put(key, mapToJsObject(value as Map<String, Any>))
                is List<*> -> jsObject.put(key, listToJsArray(value as List<Any>))
                else -> jsObject.put(key, value)
            }
        }
        return jsObject
    }

    private fun jsArrayToList(jsArray: JSArray): List<Any> {
        val list = mutableListOf<Any>()
        for (i in 0 until jsArray.length()) {
            val value = jsArray.get(i)
            list.add(
                    when (value) {
                        is JSObject -> jsObjectToMap(value)
                        is JSArray -> jsArrayToList(value)
                        else -> value
                    }
            )
        }
        return list
    }

    private fun listToJsArray(list: List<Any>): JSArray {
        val jsArray = JSArray()
        for (value in list) {
            when (value) {
                is Map<*, *> -> jsArray.put(mapToJsObject(value as Map<String, Any>))
                is List<*> -> jsArray.put(listToJsArray(value as List<Any>))
                else -> jsArray.put(value)
            }
        }
        return jsArray
    }
}
