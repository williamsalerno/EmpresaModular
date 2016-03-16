package br.com.empresa.repository.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Telefone;

public class TelefoneDocument {
    
    private TelefoneDocument(){
    }
    
    public static Document toDocument(Telefone telefone){
        if(telefone != null){
        Document telefoneDoc = new Document("ddd", telefone.getDdd())
                .append("telefone", telefone.getTelefone())
                .append("tipoTelefone", telefone.getTipoTelefone().name());
        return telefoneDoc;
        }
        return null;
    }
    
    public static List<Document> toDocument(Set<Telefone> telefones){
        if(telefones != null){
            List<Document> lista = new ArrayList<Document>();
            for(Telefone telefone : telefones){
                lista.add(toDocument(telefone));
            }
            return lista;
        }
        return null;

    }
}

