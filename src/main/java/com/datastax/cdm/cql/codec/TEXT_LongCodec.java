package com.datastax.cdm.cql.codec;

import com.datastax.oss.driver.api.core.ProtocolVersion;
import com.datastax.oss.driver.api.core.type.DataType;
import com.datastax.oss.driver.api.core.type.DataTypes;
import com.datastax.oss.driver.api.core.type.codec.TypeCodecs;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;
import com.datastax.cdm.properties.PropertyHelper;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;

public class TEXT_LongCodec extends AbstractBaseCodec<Long> {

    public TEXT_LongCodec(PropertyHelper propertyHelper) {
        super(propertyHelper);
    }

    @Override
    public @NotNull GenericType<Long> getJavaType() {
        return GenericType.LONG;
    }

    @Override
    public @NotNull DataType getCqlType() {
        return DataTypes.TEXT;
    }

    @Override
    public ByteBuffer encode(Long value, @NotNull ProtocolVersion protocolVersion) {
        if (value == null) {
            return null;
        } else {
            String stringValue = Long.toString(value);
            return TypeCodecs.TEXT.encode(stringValue, protocolVersion);
        }
    }

    @Override
    public Long decode(ByteBuffer bytes, @NotNull ProtocolVersion protocolVersion) {
        String stringValue = TypeCodecs.TEXT.decode(bytes, protocolVersion);
        return Long.parseLong(stringValue);
    }

    @Override
    public @NotNull String format(Long value) {
        return TypeCodecs.BIGINT.format(value);
    }

    @Override
    public Long parse(String value) {
        return value == null ? null : Long.parseLong(value);
    }
}
