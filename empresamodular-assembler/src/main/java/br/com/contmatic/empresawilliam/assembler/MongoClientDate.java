/******************************************************************************
 * Produto: Gestor de Empresas                                                *
 * Contmatic Phoenix © Desde 1986                                             *
 * Tecnologia em Softwares de Gestão Contábil, Empresarial e ERP              *
 * Todos os direitos reservados.                                              *
 *                                                                            *
 *                                                                            *
 *    Histórico:                                                              *
 *          Data        Programador              Tarefa                       *
 *          ----------  -----------------        -----------------------------*
 *   Autor  31/03/2016  william.salerno          Classe criada.        	      *
 *                                                                            *
 *   Comentários:                                                             *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *****************************************************************************/
package br.com.contmatic.empresawilliam.assembler;

import static com.mongodb.MongoClient.getDefaultCodecRegistry;
import static com.mongodb.MongoClientOptions.builder;
import static org.bson.BsonType.DATE_TIME;
import static org.bson.codecs.configuration.CodecRegistries.fromCodecs;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.BsonType;
import org.bson.codecs.BsonTypeClassMap;
import org.bson.codecs.DocumentCodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClientOptions;

/**
 * The Class MongoClientDate.
 *
 * @author William
 */
public class MongoClientDate {

    /**
     * Instantiates a new mongo client date.
     */
    private MongoClientDate() {

    }

    /**
     * Codec date.
     *
     * @return the mongo client options
     */
    public static MongoClientOptions codecDate() {
        Map<BsonType, Class<?>> replacements = new HashMap<BsonType, Class<?>>();
        replacements.put(DATE_TIME, Date.class);
        BsonTypeClassMap bsonTypeClassMap = new BsonTypeClassMap(replacements);
        DocumentCodecProvider documentCodecProvider = new DocumentCodecProvider(bsonTypeClassMap);
        CodecRegistry cr = fromRegistries(fromCodecs(new DateCodec()), fromProviders(documentCodecProvider), getDefaultCodecRegistry());
        MongoClientOptions option = builder().codecRegistry(cr).build();
        return option;
    }

}
