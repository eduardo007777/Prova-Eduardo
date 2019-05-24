package aplicacao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Prato;

public class Programa {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		Scanner input2 = input;
		Integer id;
		
		while(true){
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			
			System.out.println("0 para sair");
			System.out.println("1 para inserir");
			System.out.println("2 para listar");
			System.out.println("3 para alterar");
			System.out.println("4 para remover ");
			
			
			int a = input.nextInt();
			if (a == 0)
				break;
			
			else if (a == 1) {
				System.out.println("digitar descriçao");
				String descricao = input2.nextLine();
				System.out.println("digitar nome");
				String nome = input2.nextLine();
				
				Prato prato = new Prato(null, nome, descricao);
				
				entityManager.getTransaction().begin();
				entityManager.persist(prato);
				entityManager.getTransaction().commit();	
			}
			else if( a==2 ) {

				String jpql = "SELECT p FROM Pessoa p";
				List<Prato> pessoas = entityManager.createQuery(jpql,Prato.class).getResultList();
				System.out.println(pessoas);		
			}
			else if( a==3) {
				System.out.println("digitar id");
				id = input.nextInt();
				System.out.println("digitar descricao");
				String descricao = input2.nextLine();

				Prato pratoFound = entityManager.find(Prato.class,id);
				pratoFound.setDescricao(descricao);
				entityManager.getTransaction().begin();
				entityManager.persist(pratoFound);
				entityManager.getTransaction().commit();
				
			}
			else if( a==4) {
				System.out.println("digitar id");
				id = input.nextInt();

				Prato pratoFound = entityManager.find(Prato.class, id);
				entityManager.getTransaction().begin();
				entityManager.remove(pratoFound);
				entityManager.getTransaction().commit();
			}
			
			entityManager.close();
			entityManagerFactory.close();
		}
		
	}
}
