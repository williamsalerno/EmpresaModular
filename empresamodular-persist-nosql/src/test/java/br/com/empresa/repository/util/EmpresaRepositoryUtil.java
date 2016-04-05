package br.com.empresa.repository.util;

import static br.com.contmatic.empresawilliam.assembler.EmpresaObject.empresaToObject;
import static com.google.common.base.Preconditions.checkArgument;

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
        List<ValidationMessage> messages = new CNPJValidator().invalidMessagesFor(cnpj);
        checkArgument(messages.size() <= LISTA_ERROS, "Sua empresa contém erros e não pôde ser salva no banco!" + messages);
    }

    public static List<String> FieldsList(Document filtro) {
        Set<String> setKeys = filtro.keySet();
        List<String> listKeys = new ArrayList<String>();
        Iterator<String> itr = setKeys.iterator();
        while (itr.hasNext()) {
            listKeys.add(itr.next());
        }
        return listKeys;
    }

    public static List<Empresa> iterateCollection(FindIterable<Document> collection) {
        List<Empresa> empresas = new ArrayList<Empresa>();
        collection.forEach(new Block<Document>() {
            public void apply(final Document document) {
                empresas.add(empresaToObject(document));
            }
        });
        return empresas;
    }

    public static MongoDatabase getDb(MongoClient mongoClient, String db) {
        return mongoClient.getDatabase(db);
    }
}
