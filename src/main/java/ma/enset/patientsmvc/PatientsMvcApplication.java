package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import ma.enset.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {

		SpringApplication.run(PatientsMvcApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {


//			patientRepository.save(
//					new Patient(null,"maryam",new Date(),false,12));
//			patientRepository.save(
//					new Patient(null,"mohammed",new Date(),true,130));
//			patientRepository.save(
//					new Patient(null,"hajar",new Date(),false,500));

//			patientRepository.findAll().forEach(p->{
//				System.out.println(p.getId());
//				System.out.println(p.getNom());
//				System.out.println(p.getDateNaissance());
//				System.out.println(p.isMalade());
//				System.out.println(p.getScore());
//			});
//			System.out.println("***********FIND ALL PATIENTS *********************************");
//			Page<Patient> patients = patientRepository.findAll(PageRequest.of(1,5));
//			System.out.println("Total pages :"+patients.getTotalPages());
//			System.out.println("Total elements  :"+patients.getTotalElements());
//			System.out.println("Num page  :"+patients.getNumber());
//			List<Patient> content = patients.getContent();
//			content.forEach(p->{
//				System.out.println("********************************************");
//				System.out.println(p.getId());
//				System.out.println(p.getNom());
//				System.out.println(p.getDateNaissance());
//				System.out.println(p.isMalade());
//				System.out.println(p.getScore());


			//});

		};
	}

	//@Bean
	CommandLineRunner saveUsers(SecurityService securityService){
		return args -> {
			securityService.saveNewUser("basma","1234","1234");
			securityService.saveNewUser("mohamed","1234","1234");
			securityService.saveNewUser("salwa","1234","1234");
			securityService.saveNewRole("USER","");
			securityService.saveNewRole("ADMIN","");
			securityService.addRoleToUser("mohamed","ADMIN");
			securityService.addRoleToUser("mohamed","USER");
			securityService.addRoleToUser("basma","ADMIN");
			securityService.addRoleToUser("basma","USER");
			securityService.addRoleToUser("salwa","USER");
		};
	}

}
