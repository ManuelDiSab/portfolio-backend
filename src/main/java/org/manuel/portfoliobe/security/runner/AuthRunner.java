package org.manuel.portfoliobe.security.runner;

import org.manuel.portfoliobe.security.entity.ERole;
import org.manuel.portfoliobe.security.entity.Role;
import org.manuel.portfoliobe.security.repository.RoleRepository;
import org.manuel.portfoliobe.security.repository.UserRepository;
import org.manuel.portfoliobe.security.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthRunner implements ApplicationRunner {
	
	@Autowired RoleRepository roleRepository;
	@Autowired
	AuthServiceImpl authService;
	@Autowired UserRepository userRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		// Leggo nel DB se sono già presenti ruoli salvati
		List<Role> roleList = roleRepository.findAll();
		if(roleList.isEmpty()) {
//			// Metodo da lanciare solo la prima volta
//			// Serve per inizializzare i ruoli nel DB
			setRoleDefault();
		}
		Role admin =roleRepository.findByRoleName(ERole.ROLE_ADMIN).orElse(null);
		if( userRepository.findAll().isEmpty() || userRepository.findByRolesContaining(admin).isEmpty()) {
			setSuperAdmin();
		}else{
			userRepository.findByRolesContaining(admin).forEach(User -> System.out.println(User.getUsername()) );
		}
	}

	private void setRoleDefault() {
		// Creo un ruolo Admin e lo salvo nel DB
		Role admin = new Role();
		admin.setRoleName(ERole.ROLE_ADMIN);
		roleRepository.save(admin);
	}

	private void setSuperAdmin() {
		authService.createAdmin();
	}

}
