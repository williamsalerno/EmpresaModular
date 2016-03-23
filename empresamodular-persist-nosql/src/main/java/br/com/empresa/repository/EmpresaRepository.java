package br.com.empresa.repository;

import static br.com.contmatic.empresawilliam.util.ValidationUtil.hasErrors;
import static br.com.empresa.repository.util.EmpresaDocument.findToDocumentFilter;
import static br.com.empresa.repository.util.EmpresaDocument.toDocument;
import static br.com.empresa.repository.util.EmpresaDocument.updateToDocumentFilter;

import java.util.Map;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
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
            collection.deleteOne(new Document("cnpj", empresaFiltro.getCnpj()));
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

    public void buscaEmpresa(Empresa empresa) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            if (hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find();
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    Empresa empresa = EmpresaObject.empresaToObject(document);
                    System.out.println(empresa.toString());
                }
            });
        } catch (

        Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    public void buscaEmpresa(Empresa empresa, String campoBuscado) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            if (hasErrors(empresa)) {
                throw new Exception("Foi encontrado erro em Empresa.");
            }
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find(new Document(toDocument(empresa)));
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                    System.out.println(document.getString(campoBuscado));
                }
            });
        } catch (

        Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }

    public String buscaEmpresaPorCnpj(String cnpj) {
        try {

            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find(new Document("cnpj", cnpj));
            collection.forEach(new Block<Document>() {

                public String apply(final Document document) {
                    final String result;
                    result = EmpresaObject.empresaToObject(document).toString();
                }

            });
            return result;
        } finally {
            mongoClient.close();
        }

    }

    public Empresa buscaEmpresaPor(String cnpj) {
        try {
            this.mongoClient = new MongoClient(this.host + ":" + this.port, MongoClientDate.codecDate());
            MongoDatabase database = mongoClient.getDatabase(this.db);
            FindIterable<Document> collection = database.getCollection(COLLECTION).find(new Document("cnpj", cnpj));
            collection.forEach(new Block<Document>() {
                public void apply(final Document document) {
                }
            });
        } finally {
            mongoClient.close();
        }
    }

}
