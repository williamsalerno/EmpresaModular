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

import static br.com.contmatic.empresawilliam.EnderecoType.COMERCIAL;
import static br.com.contmatic.empresawilliam.EnderecoType.RESIDENCIAL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import br.com.contmatic.empresawilliam.Endereco;
import br.com.contmatic.empresawilliam.EnderecoType;

/**
 * The Class EnderecoAssembler.
 *
 * @author William
 */
public class EnderecoAssembler {

    /**
     * Instantiates a new endereco assembler.
     */
    private EnderecoAssembler() {
    }

    /**
     * To document.
     *
     * @param endereco the endereco
     * @return the document
     */
    public static Document enderecoToDocument(Endereco endereco) {
        if (endereco != null && endereco.getTipoEndereco() != null) {
            Document enderecoDoc = new Document("tipoLogradouro", endereco.getTipoLogradouro()).append("nomeLogradouro", endereco.getNomeLogradouro())
                    .append("numeroEndereco", endereco.getNumeroEndereco()).append("cep", endereco.getCep()).append("tipoEndereco", endereco.getTipoEndereco().name());
            return enderecoDoc;
        }
        return null;
    }

    /**
     * To document.
     *
     * @param enderecos the enderecos
     * @return the list
     */
    public static List<Document> listaEnderecosToDocument(Set<Endereco> enderecos) {
        if (enderecos != null) {
            List<Document> lista = new ArrayList<Document>();
            for(Endereco endereco : enderecos) {
                lista.add(enderecoToDocument(endereco));
            }
            return lista;
        }
        return null;

    }

    /**
     * Document to endereco.
     *
     * @param document the document
     * @return the endereco
     */
    public static Endereco documentToEndereco(Document document) {
        Endereco endereco = new Endereco();
        if (document != null) {
            endereco.setTipoLogradouro(document.getString("tipoLogradouro"));
            endereco.setNomeLogradouro(document.getString("nomeLogradouro"));
            endereco.setNumeroEndereco(document.getInteger("numeroEndereco"));
            endereco.setCep(document.getString("cep"));
            endereco.setTipoEndereco(tipoEndereco(document));
            return endereco;
        }
        return null;
    }

    /**
     * To endereco.
     *
     * @param listaEndereco the lista endereco
     * @return the set
     */
    public static Set<Endereco> toEndereco(List<Document> listaEndereco) {
        Set<Endereco> enderecos = new HashSet<Endereco>();
        if (listaEndereco != null) {
            for(Document doc : listaEndereco) {
                enderecos.add(documentToEndereco(doc));
            }
            return enderecos;
        }

        return null;
    }

    /**
     * Tipo endereco.
     *
     * @param doc the doc
     * @return the endereco type
     */
    private static EnderecoType tipoEndereco(Document document) {
        if (document != null) {
            if (document.get("tipoEndereco").equals(COMERCIAL.name())) {
                return COMERCIAL;
            } else {
                return RESIDENCIAL;
            }
        }
        return null;
    }
}
