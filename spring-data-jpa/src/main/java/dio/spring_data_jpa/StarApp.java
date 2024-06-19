package dio.spring_data_jpa;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dio.spring_data_jpa.model.User;
import dio.spring_data_jpa.repository.UserRespository;

@Component
public class StarApp implements CommandLineRunner{

    @Autowired
    private UserRespository repository;
    @Override
    public void run(String... args) throws Exception {
        //insert();
        //showUsers();
        //update();
        //delete();
        
    }

    public  void insert(){
        Scanner s = new Scanner(System.in);
        User user = new User();

        System.out.println("Digite seu nome: ");
        String nome = s.nextLine();

        System.out.println("Digite seu email: ");
        String email = s.nextLine();

        s.close();

        user.setNome(nome);
        user.setEmail(email);

        repository.save(user);
    }

    public void showUsers(){
        for(User u : repository.findAll()){
            System.out.println(u);
        }
    }

    public void update(){
        Scanner s = new Scanner(System.in);

        System.out.println("Informe o id do usuario que voce deseja mudar: ");
        int id = s.nextInt();

        s.nextLine();
        System.out.println("Digite o novo nome: ");
        String novo_nome = s.nextLine();

        System.out.println("Digite o novo email: ");
        String novo_email = s.nextLine();

        s.close();

        for(User u : repository.findAll()){
            if(u.getId() == id){
                u.setNome(novo_nome);
                u.setEmail(novo_email);
                repository.save(u);
            }
        }
    }

    public void delete(){
        Scanner s = new Scanner(System.in);

        System.out.println("Informe o id do usuario que voce deseja deletar: ");
        int id = s.nextInt();

        s.close();
        for(User u : repository.findAll()){
            if(u.getId() == id){
                repository.delete(u);
            }
        }
    }
    
}
