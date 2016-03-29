package br.com.empresa.repository;

import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.findToDocumentFilter;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.toDocument;
import static br.com.contmatic.empresawilliam.assembler.EmpresaAssembler.updateToDocumentFilter;
import static br.com.contmatic.empresawilliam.assembler.EmpresaObject.empresaToObject;
import static br.com.contmatic.empresawilliam.assembler.MongoClientDate.codecDate;
import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.util.ValidationUtil;

/**
 * The Class EmpresaRepository.
 *
 * @author William
 */
public class EmpresaRepository {

    /** The host. */
    private String host;

    /** The port. */
    private int port;

    /** The db. */
    private String db;

    /** The Constant COLLECTION. */
    public static final String COLLECTION = "empresa";

    /** The mongo client. */
    private MongoClient mongoClient;

    /**
     * Instantiates a new empresa repository.
     *
     * @param host the host
     * @param port the port
     * @param db the db
     */
    public EmpresaRepository(String host, int port, String db) {
        this.host = host;
        this.port = port;
        this.db = db;
    }

    /**
     * Save empresa.
     *
     * @param empresa the empresa
     */
    public void saveEmpresa(Empresa empresa) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            if (hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.insertOne(toDocument(empresa));
        } catch (MongoWriteException e) {
            throw new IllegalStateException("Empresa já existe.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Update empresa.
     *
     * @param empresaFiltro the empresa filtro
     * @param empresa the empresa
     */
    public void updateEmpresa(Empresa empresaFiltro, Empresa empresa) {
        try {
            if (ValidationUtil.hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Update empresas.
     *
     * @param empresaFiltro the empresa filtro
     * @param empresa the empresa
     */
    public void updateEmpresas(Empresa empresaFiltro, Empresa empresa) {
        try {
            if (ValidationUtil.hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            empresa.setRazaoSocial("asdhuahsd");
            collection.updateMany(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Remove empresa.
     *
     * @param empresaFiltro the empresa filtro
     */
    public void removeEmpresa(Empresa empresaFiltro) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteOne(new Document("_id", empresaFiltro.getCnpj()));
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Remove empresas.
     */
    public void removeEmpresas() {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteMany(new Document());
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Busca empresa.
     *
     * @param empresa the empresa
     * @return the list
     */
    public List<Empresa> buscaEmpresa(Empresa empresa) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find();
            final List<Empresa> empresas = new ArrayList<Empresa>();
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally {
            mongoClient.close();
        }

    }

    /**
     * Busca empresa por.
     *
     * @param key the key
     * @return the map
     */
    public Map<String, String> buscaEmpresaPor(List<String> key) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            Map<String, String> mapEmpresas = new HashMap<String, String>();
            FindIterable<Document> collection;
            List<Integer> is = new ArrayList<>();
            for(int i = 0 ; i < key.size() ; i++) {
                is.add(i);
            }
            collection = database.getCollection(COLLECTION).find().projection(fields(include(key), include("_id")));
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    for(final int i : is) {
                        mapEmpresas.put(key.get(i), document.getString(key.get(i)));
                    }
                }
            });
            return mapEmpresas;
        } finally {
            mongoClient.close();
        }
    }

    /**
     * Busca empresa por.
     *
     * @param empresaFiltro the empresa filtro
     * @return the list
     */
    public List<Empresa> buscaEmpresaPor(Empresa empresaFiltro) {
        try {
            List<Empresa> empresas = new ArrayList<Empresa>();
            Document doc = new Document(updateToDocumentFilter(empresaFiltro));
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            Set<String> setKeys = doc.keySet();
            List<String> listKeys = new ArrayList<String>();
            Iterator<String> itr = setKeys.iterator();
            while (itr.hasNext()) {
                listKeys.add(itr.next());
            }
            FindIterable<Document> collection;
            collection = database.getCollection(COLLECTION).find().projection(fields(include("_id"), include(listKeys)));
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally

        {
            mongoClient.close();
        }
    }

    /**
     * Paginar buscas.
     *
     * @param empresaFiltro the empresa filtro
     * @param elementosPorPagina the elementos por pagina
     * @return the list
     */
    public List<Empresa> paginarBuscas(Empresa empresaFiltro, int elementosPorPagina) {
        try {
            List<Empresa> empresas = new ArrayList<Empresa>();
            Document doc = new Document(updateToDocumentFilter(empresaFiltro));
            this.mongoClient = new MongoClient(this.host + ":" + this.port, codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            Set<String> setKeys = doc.keySet();
            List<String> listKeys = new ArrayList<String>();
            Iterator<String> itr = setKeys.iterator();
            while (itr.hasNext()) {
                listKeys.add(itr.next());
            }
            FindIterable<Document> collection;
            collection = database.getCollection(COLLECTION).find().skip(0).limit(elementosPorPagina);
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(empresaToObject(document));
                }
            });
            return empresas;
        } finally

        {
            mongoClient.close();
        }
    }
}
