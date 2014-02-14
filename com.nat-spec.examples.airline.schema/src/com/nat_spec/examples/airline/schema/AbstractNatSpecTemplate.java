package com.nat_spec.examples.airline.schema;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.nat_spec.examples.airline.schema.ddl.Table;
import com.nat_spec.examples.airline.schema.ddl.codegen.SQLCodeGenerator;

public abstract class AbstractNatSpecTemplate {

	protected void run() throws IOException {
		SQLCodeGenerator generator = new SQLCodeGenerator();

		createDataModel();
		List<Table> tables = getDataModelSupport().getAllTables();
		String code = "";
		for (Table table : tables) {
			code += generator.generateCode(table);
		}

		String path = "src-gen" + File.separator + "create_schema.sql";
		File file = new File(path);
		FileWriter writer = new FileWriter(file);
		writer.write(code);
		writer.close();
	}

	protected abstract void createDataModel();

	protected abstract AbstractSchemaSupport getDataModelSupport();
}
