package net.iamtakagi.kodaka.adapter.impl

import net.iamtakagi.kodaka.adapter.CommandTypeAdapter
class IntegerTypeAdapter : CommandTypeAdapter {
    override fun <T> convert(string: String, type: Class<T>): T {
        return type.cast(string.toIntOrNull())
    }
}