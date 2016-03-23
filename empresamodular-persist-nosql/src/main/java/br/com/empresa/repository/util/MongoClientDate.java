package br.com.empresa.repository.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.joda.time.LocalDate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

public class MongoClientDate {

    private MongoClientDate() {

    }

    public static MongoClientOptions codecDate() {
        Map<BsonType, Class<?>> replacements = new HashMap<BsonType, Class<?>>();
        replacements.put(BsonType.DATE_TIME, Date.class);
        BsonTypeClassMap bsonTypeClassMap = new BsonTypeClassMap(replacements);
        DocumentCodecProvider documentCodecProvider = new DocumentCodecProvider(bsonTypeClassMap);
        CodecRegistry cr = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new DateCodec()), CodecRegistries.fromProviders(documentCodecProvider), MongoClient.getDefaultCodecRegistry());
        MongoClientOptions option = MongoClientOptions.builder().codecRegistry(cr).build();
        return option;
    }

    // public static MongoClientOptions codecLocalDate() {
    // Map<BsonType, Class<?>> replacements = new HashMap<BsonType, Class<?>>();
    // replacements.put(BsonType.DATE_TIME, LocalDate.class);
    // BsonTypeClassMap bsonTypeClassMap = new BsonTypeClassMap(replacements);
    // DocumentCodecProvider documentCodecProvider = new DocumentCodecProvider(bsonTypeClassMap);
    // CodecRegistry cr = CodecRegistries.fromRegistries(CodecRegistries.fromCodecs(new LocalDateCodec()), CodecRegistries.fromProviders(documentCodecProvider),
    // MongoClient.getDefaultCodecRegistry());
    // MongoClientOptions option = MongoClientOptions.builder().codecRegistry(cr).build();
    // return option;
    // }

}