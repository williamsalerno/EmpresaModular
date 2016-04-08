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

public class EmpresaRepositoryUtil {

    private static final int LISTA_ERROS = 0;

    private EmpresaRepositoryUtil() {
    }

    public static void validateCnpj(String cnpj) {
        checkNotNull(cnpj, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        List<ValidationMessage> messages = new CNPJValidator().invalidMessagesFor(cnpj);
        checkArgument(messages.size() <= LISTA_ERROS, "Sua empresa contém erros e não pôde ser salva no banco!" + messages);
    }

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

    public static MongoDatabase getDb(MongoClient mongoClient, String db) {
        checkNotNull(mongoClient, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        checkNotNull(db, "Esse método é utilitário. Não deve aceitar null como parâmetro.");
        return mongoClient.getDatabase(db);
    }
}
