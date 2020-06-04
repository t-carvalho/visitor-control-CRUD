package application;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.VisitorDao;
import model.entities.Dweller;
import model.entities.Visitor;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Calendar entrada = Calendar.getInstance();
		Calendar saida = Calendar.getInstance();
		int horaEntrada = entrada.get(Calendar.HOUR_OF_DAY);
		int horaSaida = saida.get(Calendar.HOUR_OF_DAY);
		
		VisitorDao visitorDao = DaoFactory.createVisitorDao();
		
		System.out.println("=== Test 1: seller incert =====");
		Dweller newDweller = new Dweller(2, "2");
		Visitor newVisitor = new Visitor(null,"Greg", horaEntrada, horaSaida, newDweller);
		visitorDao.insert(newVisitor);
		System.out.println("Inserted! New id = " + newVisitor.getId());
	}

}
