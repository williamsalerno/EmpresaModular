package br.com.empresa.repository.util;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;

public class EmpresaUtil {

    private static final int LISTA_ERROS = 0;

    private EmpresaUtil() {

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

}
