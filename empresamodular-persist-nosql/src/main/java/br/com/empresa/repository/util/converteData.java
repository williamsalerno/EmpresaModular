package br.com.empresa.repository.util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.bson.BSON;
import org.bson.Transformer;

import java.net.UnknownHostException;
import java.util.Date;
import org.joda.time.LocalDate;

public class converteData {

    public void converteParaISODate() {
        // Add hook to encode Joda DateTime as a java.util.Date
        BSON.addEncodingHook(LocalDate.class, new Transformer() {
            @Override
            public Object transform(final Object o) {
                return new Date().getTime();
            }
        });
        // Add hook to decode java.util.Date as a Joda DateTime
        BSON.addDecodingHook(Date.class, new Transformer() {
            @Override
            public Object transform(final Object o) {
                return new LocalDate(((Date) o).getTime());
            }
        });

        MongoClient mongoClient = new MongoClient();
        DBCollection c = mongoClient.getDB("test").getCollection("joda");
        c.drop();

        // insert a document containing a Joda DateTime
        c.insert(new BasicDBObject("createdAt", new DateTime()));

        DBObject doc = c.findOne();

        // Note that when querying for this document, it will come back as a Joda DateTime as well.
        System.out.println(doc.get("createdAt").getClass());
    }
}
