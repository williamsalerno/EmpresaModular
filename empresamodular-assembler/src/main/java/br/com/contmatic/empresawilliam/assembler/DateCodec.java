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

import static com.google.common.base.Preconditions.checkNotNull;
import static java.time.Instant.ofEpochMilli;

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

    /*
     * (non-Javadoc)
     * 
     * @see org.bson.codecs.Encoder#encode(org.bson.BsonWriter, java.lang.Object, org.bson.codecs.EncoderContext)
     */
    public void encode(BsonWriter writer, Date value, EncoderContext encoderContext) {
        checkNotNull(writer, "Este método é utilitário, não deve ser invocado e nem deve receber valor null.");
        checkNotNull(value, "Este método é utilitário, não deve ser invocado e nem deve receber valor null.");
        checkNotNull(encoderContext, "Este método é utilitário, não deve ser invocado e nem deve receber valor null.");
        writer.writeDateTime(value.getTime());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bson.codecs.Encoder#getEncoderClass()
     */
    public Class<Date> getEncoderClass() {
        return Date.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.bson.codecs.Decoder#decode(org.bson.BsonReader, org.bson.codecs.DecoderContext)
     */
    public Date decode(BsonReader reader, DecoderContext decoderContext) {
        checkNotNull(reader, "Este método é utilitário, não deve ser invocado e nem deve receber valor null.");
        checkNotNull(decoderContext, "Este método é utilitário, não deve ser invocado e nem deve receber valor null.");
        return Date.from(ofEpochMilli(reader.readDateTime()));
    }

}
