package br.com.projetocompiladores.ast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import br.com.projetocompiladores.datastructures.ProjSymbol;
import br.com.projetocompiladores.datastructures.ProjSymbolTable;

public class ProjProgram {
	private ProjSymbolTable varTable;
	private ArrayList<AbstractCommand> comandos;
	private String programName;
	
	public void generateTarget() {
		StringBuilder str = new StringBuilder();
		str.append("import java.util.Scanner;\n");
		str.append("public class MainClass{ \n");
		str.append("  public static void main(String args[]){\n ");
		str.append("      Scanner _key = new Scanner(System.in);\n");
		for (ProjSymbol symbol: varTable.getAll()) {
			str.append(symbol.generateJavaCode()+"\n");
		}
		for (AbstractCommand command: comandos) {
			str.append(command.generateJavaCode()+"\n");
		}
		str.append("}");
		str.append("}");
		
		try {
			FileWriter fr = new FileWriter(new File("MainClass.java"));
			fr.write(str.toString());
			fr.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	public ProjSymbolTable getVarTable() {
		return varTable;
	}

	public void setVarTable(ProjSymbolTable varTable) {
		this.varTable = varTable;
	}

	public ArrayList<AbstractCommand> getComandos() {
		return comandos;
	}

	public void setComandos(ArrayList<AbstractCommand> comandos) {
		this.comandos = comandos;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
}
