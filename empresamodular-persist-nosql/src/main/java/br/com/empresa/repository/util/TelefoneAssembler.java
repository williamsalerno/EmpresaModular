package br.com.empresa.repository.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Telefone;
import br.com.contmatic.empresawilliam.TelefoneType;

public class TelefoneAssembler {

    private TelefoneAssembler() {
    }

    public static Document toDocument(Telefone telefone) {
        if (telefone != null) {
            Document telefoneDoc = new Document("ddd", telefone.getDdd()).append("telefone", telefone.getTelefone()).append("tipoTelefone", telefone.getTipoTelefone().name());
            return telefoneDoc;
        }
        return null;
    }

    public static Telefone documentToTelefone(Document document) {
        Telefone telefone = new Telefone();
        if (document == null) {
            return null;
        } else {
            telefone.setTelefone(document.getString("telefone"));
            telefone.setDdd(document.getInteger("ddd"));
            telefone.setTipoTelefone(tipoTelefone(document));
            return telefone;
        }
    }

    public static List<Document> toDocument(Set<Telefone> telefones) {
        if (telefones != null) {
            List<Document> lista = new ArrayList<Document>();
            for(Telefone telefone : telefones) {
                lista.add(toDocument(telefone));
            }
            return lista;
        }
        return null;
    }

    public static Set<Telefone> toTelefone(List<Document> listaTelefone) {
        Set<Telefone> telefones = new HashSet<Telefone>();
        if (listaTelefone != null) {
            for(Document doc : listaTelefone) {
                telefones.add(documentToTelefone(doc));
            }
            return telefones;
        }
        return null;
    }

    public static TelefoneType tipoTelefone(Document doc) {
        if (doc.get("tipoTelefone").equals(TelefoneType.CELULAR.name())) {
            return TelefoneType.CELULAR;
        } else {
            return TelefoneType.FIXO;
        }

    }
}
