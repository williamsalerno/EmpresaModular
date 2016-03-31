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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Telefone;
import br.com.contmatic.empresawilliam.TelefoneType;

/**
 * The Class TelefoneAssembler.
 *
 * @author William
 */
public class TelefoneAssembler {

    /**
     * Instantiates a new telefone assembler.
     */
    private TelefoneAssembler() {
    }

    /**
     * To document.
     *
     * @param telefone the telefone
     * @return the document
     */
    public static Document toDocument(Telefone telefone) {
        if (telefone != null) {
            Document telefoneDoc = new Document("ddd", telefone.getDdd()).append("telefone", telefone.getTelefone()).append("tipoTelefone", telefone.getTipoTelefone().name());
            return telefoneDoc;
        }
        return null;
    }

    /**
     * Document to telefone.
     *
     * @param document the document
     * @return the telefone
     */
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

    /**
     * To document.
     *
     * @param telefones the telefones
     * @return the list
     */
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

    /**
     * To telefone.
     *
     * @param listaTelefone the lista telefone
     * @return the set
     */
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

    /**
     * Tipo telefone.
     *
     * @param doc the doc
     * @return the telefone type
     */
    public static TelefoneType tipoTelefone(Document doc) {
        if (doc.get("tipoTelefone").equals(TelefoneType.CELULAR.name())) {
            return TelefoneType.CELULAR;
        } else {
            return TelefoneType.FIXO;
        }

    }
}
