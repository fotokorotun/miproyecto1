package cliente;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class TestClienteTarjeta implements CommandLineRunner{
	
	
		@Autowired
		private ClienteRepository clienteRepository;
		@Autowired
		private TarjetaRepository tarjetaRepository;
		

		public void run(String... args) throws Exception {

			
			  Cliente isa = new Cliente("Isabel", "GoogleLand", 25, "@isa", 1000.00, "11234");
			  Cliente joan = new Cliente("Joan", "PreGoogleLand", 25, "@joan",1000.00, "14");
		
			  clienteRepository.save(isa);
			  clienteRepository.save(joan);
			  
			 
			  
			  Tarjeta nomina1 = new Tarjeta(378642376,new Date(),"bbva","debito",143,"master");
			  Tarjeta nomina2 = new Tarjeta(37865653,new Date(),"santander","debito",153,"visa");
			 
				
			  
			  tarjetaRepository.save(nomina1);
			  tarjetaRepository.save(nomina2);
			  
			  
			  
			  
			  joan.addTarjeta(nomina1);
			  joan.addTarjeta(nomina2);
			  
			 clienteRepository.save(joan);
			 clienteRepository.save(isa);
			  
			 
			  
			 
			  
		
			
			
		}

		

		

		
	
}
