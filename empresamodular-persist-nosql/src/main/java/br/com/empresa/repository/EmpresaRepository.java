package br.com.empresa.repository;

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.empresa.repository.util.EmpresaDocument.findToDocumentFilter;
import static br.com.empresa.repository.util.EmpresaDocument.toDocument;
import static br.com.empresa.repository.util.EmpresaDocument.updateToDocumentFilter;
import static br.com.empresa.repository.util.EmpresaObject.empresaToObject;
import static com.mongodb.client.model.Projections.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import br.com.contmatic.empresawilliam.Empresa;
import br.com.contmatic.empresawilliam.util.ValidationUtil;
import br.com.empresa.repository.util.EmpresaObject;
import br.com.empresa.repository.util.MongoClientDate;

public class EmpresaRepository {

    private String host;
    private int port;
    private String db;
    public static final String COLLECTION = "empresa";
    private MongoClient mongoClient;

    public EmpresaRepository(String host, int port, String db) {
        this.host = host;
        this.port = port;
        this.db = db;
    }

    public void saveEmpresa(Empresa empresa) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
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

    public void updateEmpresa(Empresa empresaFiltro, Empresa empresa) {
        try {
            if (ValidationUtil.hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.updateOne(findToDocumentFilter(empresaFiltro), new Document("$set", updateToDocumentFilter(empresa)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    public void updateEmpresas(Empresa empresaFiltro, Empresa empresa) {
        try {
            if (ValidationUtil.hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
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

    public void removeEmpresas(Empresa empresaFiltro) {
        try {
            this.mongoClient = new MongoClient(this.host, this.port);
            MongoDatabase database = mongoClient.getDatabase(this.db);
            MongoCollection<Document> collection = database.getCollection(COLLECTION);
            collection.deleteMany(findToDocumentFilter(empresaFiltro));
        } finally {
            mongoClient.close();
        }
    }

    public List<Empresa> buscaEmpresa(Empresa empresa) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find();
            final List<Empresa> empresas = new ArrayList<>();
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    empresas.add(EmpresaObject.empresaToObject(document));
                }
            });
            return empresas;
        } finally {
            mongoClient.close();
        }

    }

    public List<Empresa> buscaEmpresaPorCnpj(String cnpj) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find(new Document("_id", cnpj));
            final List<Empresa> empresas = new ArrayList<>();
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

    public List<Empresa> buscaEmpresaPor(List<String> key, Integer filtro) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            final List<Empresa> empresas = new ArrayList<>();
            FindIterable<Document> collection;
            for(int i = 0 ; i < key.size() ; i++) {
                collection = database.getCollection(COLLECTION).find(new Document(key.get(i), filtro));
                collection.forEach(new Block<Document>() {
                    public void apply(final Document document) {
                        empresas.add(empresaToObject(document));
                    }
                });
            }
            return empresas;
        } finally {
            mongoClient.close();
        }
    }

    public Map<String, String> buscaEmpresaPor(List<String> key) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            Map<String, String> empresas = new HashMap<>();
            FindIterable<Document> collection;
            List<Integer> is = new ArrayList<>();
            List<Empresa> empresa = new ArrayList<>();
            for(int i = 0 ; i < key.size() ; i++)
                is.add(i);
            for(final int i : is) {
                collection = database.getCollection(COLLECTION).find().projection(include(key));
                collection.forEach(new Block<Document>() {
                    public void apply(final Document document) {
                        empresas.put(key.get(i), document.getString(key.get(i)));
                    }
                });
            }
            return empresas;
        } finally {
            mongoClient.close();
        }
    }
}
