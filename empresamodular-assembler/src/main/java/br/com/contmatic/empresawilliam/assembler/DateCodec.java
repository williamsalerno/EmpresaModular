package br.com.contmatic.empresawilliam.assembler;

import java.time.Instant;
import java.util.Date;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * The Class DateCodec.
 *
 * @author William
 */
public class DateCodec implements Codec<Date> {

    /* (non-Javadoc)
     * @see org.bson.codecs.Encoder#encode(org.bson.BsonWriter, java.lang.Object, org.bson.codecs.EncoderContext)
     */
    public void encode(BsonWriter writer, Date value, EncoderContext encoderContext) {
        writer.writeDateTime(value.getTime());
    }

    /* (non-Javadoc)
     * @see org.bson.codecs.Encoder#getEncoderClass()
     */
    public Class<Date> getEncoderClass() {
        return Date.class;
    }

    /* (non-Javadoc)
     * @see org.bson.codecs.Decoder#decode(org.bson.BsonReader, org.bson.codecs.DecoderContext)
     */
    public Date decode(BsonReader reader, DecoderContext decoderContext) {
        return Date.from(Instant.ofEpochMilli(reader.readDateTime()));
    }

}
