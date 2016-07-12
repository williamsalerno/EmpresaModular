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
 *   Autor  19/04/2016  william.salerno          Classe criada.        	      *
 *                                                                            *
 *   Comentários:                                                             *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *                                                                            *
 *****************************************************************************/
package br.com.empresa.repository.util;

import static br.com.contmatic.empresawilliam.assembler.EmpresaObject.empresaToObject;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import br.com.contmatic.empresawilliam.Empresa;

/**
 * The Class EmpresaRepositoryUtil.
 *
 * @author williamsalerno
 */
public class EmpresaRepositoryUtil {

    /** The Constant LISTA_ERROS. */
    private static final int LISTA_ERROS = 0;

    /**
     * Instantiates a new empresa repository util.
     */
    private EmpresaRepositoryUtil() {
    }

    /**
     * Validate cnpj.
     *
     * @param cnpj the cnpj
     */
    public static void validateCnpj(String cnpj) {
        checkNotNull(cnpj, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        List<ValidationMessage> messages = new CNPJValidator().invalidMessagesFor(cnpj);
        checkArgument(messages.size() <= LISTA_ERROS, "Sua empresa contém erros e não pôde ser salva no banco!" + messages);
    }

    /**
     * Fields list.
     *
     * @param filtro the filtro
     * @return the list
     */
    public static List<String> FieldsList(Document filtro) {
        checkNotNull(filtro, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        Set<String> setKeys = filtro.keySet();
        List<String> listKeys = new ArrayList<String>();
        Iterator<String> itr = setKeys.iterator();
        while (itr.hasNext()) {
            listKeys.add(itr.next());
        }
        return listKeys;
    }

    /**
     * Iterate collection.
     *
     * @param collection the collection
     * @return the list
     */
    public static List<Empresa> iterateCollection(FindIterable<Document> collection) {
        checkNotNull(collection, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        List<Empresa> empresas = new ArrayList<Empresa>();
        collection.forEach(new Block<Document>() {
            public void apply(final Document document) {
                empresas.add(empresaToObject(document));
            }
        });
        return empresas;
    }

    /**
     * Gets the db.
     *
     * @param mongoClient the mongo client
     * @param db the db
     * @return the db
     */
    public static MongoDatabase getDb(MongoClient mongoClient, String db) {
        checkNotNull(mongoClient, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        checkNotNull(db, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        return mongoClient.getDatabase(db);
    }
}
