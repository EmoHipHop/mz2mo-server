package com.emo_hip_hop.mz2mo.global.error.adapter.input.web

import com.emo_hip_hop.mz2mo.global.error.domain.ErrorCode
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider

class ErrorCodeSerializer : JsonSerializer<ErrorCode>() {

    override fun serialize(value: ErrorCode?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        if (value == null || gen == null) return
        gen.writeStartObject()
        gen.writeStringField("name", value.name)

        gen.writeObjectFieldStart("domain")
        gen.writeStringField("name", value.domain.name)
        gen.writeNumberField("code", value.domain.code)
        gen.writeEndObject()

        gen.writeObjectFieldStart("cause")
        gen.writeStringField("name", value.cause.name)
        gen.writeNumberField("code", value.cause.code)
        gen.writeEndObject()

        gen.writeNumberField("index", value.index)

        gen.writeEndObject()
    }
}