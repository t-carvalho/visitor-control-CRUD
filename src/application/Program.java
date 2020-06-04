package application;

import java.util.Calendar;
import java.util.List;
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
		
		//System.out.println("=== Test 1: seller incert =====");
		//Dweller newDweller = new Dweller(2, "2");
		//Visitor newVisitor = new Visitor(null,"Greg", horaEntrada, horaSaida, newDweller);
		//visitorDao.insert(newVisitor);
		//System.out.println("Inserted! New id = " + newVisitor.getId());
		
		System.out.println("=== Test 2: visitor findById =====");
		Visitor visitor = visitorDao.findById(2);
		System.out.println(visitor);
		
		//System.out.println("=== Test 3: visitor findAll =====");
		//List<Visitor> list;
		//list = visitorDao.findAll();
		//for (Visitor obj: list) {
		//	System.out.println(obj);
		//}
		
		//System.out.println("=== Test 4: visitor update =====");
		//visitorDao.findById(3);
		//visitor.setName("Aline de Moraes");
		//visitorDao.update(visitor);
		//System.out.println("Update completed");
		
		System.out.println("=== Test 5: visitor delete =====");
		System.out.println("Enter codigo for delete test: ");
		int id = sc.nextInt();
		visitorDao.deleteById(id);
		System.out.println("Delete completed");
	}

}
