package cliente;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Optional;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ClienteImageController {

			@Autowired
			private ClienteImageRepository clienteImageRepository;

			// -----------------------------------CRUD: read, all EmployeeImages--------------------------------------------------
			// -------------------------------------------------------------------------------------------------------------------
			@GetMapping("/getAllClienteImages")
			public Iterable<ClienteImage> getAllClientes() {

				return clienteImageRepository.findAll();

			}

			private Iterable<ClienteImage> findAll() {
				// TODO Auto-generated method stub
				return null;
			}

			// -----------------------------------CRUD: create, EmployeeImage-----------------------------------------------------
			// -------------------------------------------------------------------------------------------------------------------
			@PostMapping("/clienteImage")
			public ClienteImage addClienteImage(@RequestParam String name, @RequestParam MultipartFile file)
					throws IOException {

				ClienteImage clienteImage = new ClienteImage();
				System.out.println("id of clienteIMage: " +  clienteImage.getId());
				clienteImage.setName(name);
				clienteImage.setImage(new Binary(file.getBytes()));
				
				
				
				ClienteImage clienteImageSaved =  clienteImageRepository.save(clienteImage);
				System.out.println("id of clienteIMage: " +  clienteImageSaved.getId());
				
				//employee.setFID_employee(employeeImageSaved.getId());
				//employeeRepository.save(employee);
				
				
				return clienteImageSaved;
				
			}

			

			// -----------------------------------CRUD: read, EmployeeImage-------------------------------------------------------
			// -------------------------------------------------------------------------------------------------------------------
			@GetMapping("/getClienteImageData")
			public String getClienteImageData(@RequestParam String id) {

				Optional<ClienteImage> clienteImage = clienteImageRepository.findById(id);
				Encoder encoder = Base64.getEncoder();

				return encoder.encodeToString(clienteImage.get().getImage().getData());

			}

			@GetMapping("/getClienteImage")
			public ResponseEntity<byte[]> getClienteImage(@RequestParam String id) throws SQLException {

				Optional<ClienteImage> clienteImage = clienteImageRepository.findById(id);

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_JPEG);
				return new ResponseEntity<>(clienteImage.get().getImage().getData(), headers, HttpStatus.OK);

			}

			private Optional<ClienteImage> findById(String id) {
				// TODO Auto-generated method stub
				return null;
			}

			
			
			
			


		}
		
		



