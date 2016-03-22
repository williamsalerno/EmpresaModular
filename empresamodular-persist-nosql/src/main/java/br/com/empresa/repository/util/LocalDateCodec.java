package br.com.empresa.repository.util;

import java.util.Date;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.joda.time.LocalDate;

// public class LocalDateCodec implements Codec<LocalDate> {

// @Override
// public void encode(BsonWriter writer, LocalDate value, EncoderContext encoderContext) {
// writer.writeDateTime(value.getDayOfMonth());
// }
//
// @Override
// public Class<LocalDate> getEncoderClass() {
// return LocalDate.class;
// }
//
// @Override
// public LocalDate decode(BsonReader reader, DecoderContext decoderContext) {
// Date data = new Date(reader.readDateTime());
// return LocalDate.fromDateFields(data);
// }

// }
