package br.com.empresa.repository.util;

import java.time.Instant;
import java.util.Date;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

public class DateCodec implements Codec<Date> {

    public void encode(BsonWriter writer, Date value, EncoderContext encoderContext) {
        writer.writeDateTime(value.getTime());
    }

    public Class<Date> getEncoderClass() {
        return Date.class;
    }

    public Date decode(BsonReader reader, DecoderContext decoderContext) {
        return Date.from(Instant.ofEpochMilli(reader.readDateTime()));
    }

}
