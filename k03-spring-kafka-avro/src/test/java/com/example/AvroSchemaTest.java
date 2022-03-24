package com.example;

//import com.example.domain.Customer;
import com.example.domain.EmployeeTask;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.apache.avro.Schema;
import org.junit.jupiter.api.Test;

public class AvroSchemaTest {

    /*
    Podemos generar los esquemas AVRO automáticamente con este código.
    Una vez generado el esquema avsc lo movemos a la carpeta src/main/resources/avro
    Una vez creado el archivo avsc ya podemos borrar la clase java de nuestro código
     */
    @Test
    void generateCustomerSchema() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        AvroSchemaGenerator generator = new AvroSchemaGenerator();

        mapper.acceptJsonFormatVisitor(EmployeeTask.class, generator);
        Schema avroSchema = generator.getGeneratedSchema().getAvroSchema();

        System.out.println(avroSchema.toString(true));
    }
}
