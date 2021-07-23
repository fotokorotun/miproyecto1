package cliente;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TarjetaRestController {
	@Autowired
	TarjetaRepository tarjetaRepository;

	// ---------------------- crud: READ -------------------------------------------
	// -----------------------------------------------------------------------------
	@GetMapping("/allTarjetas")
	public Iterable<Tarjeta> getAlltarjeta() {

		return tarjetaRepository.findAll();
	}

	// ---------------------- crud: READ (by Id) ---------------------------------
	// ---------------------------------------------------------------------------
	@GetMapping("/getTarjeta/{id}")
	public Tarjeta findById(@PathVariable int id) {

		Optional<Tarjeta> tarjetaFound = tarjetaRepository.findById(id);

		if (tarjetaFound.isPresent()) {

			return tarjetaFound.get();
		}

		return null;
	}

//---------------------- crud: DELETE (by Id) -------------------------------
	// ----------------------------------------------------------------------------
	@DeleteMapping("/deleteTarjeta/{id}")
	public void deletTarjeta(@PathVariable int id) {

		Optional<Tarjeta> tarjetaFound = tarjetaRepository.findById(id);

		if (tarjetaFound.isPresent()) {

			tarjetaRepository.deleteById(id);
		}

	}

	// ---------------------- crud: CREATE (by Id) -------------------------------
	// ---------------------------------------------------------------------------
	@PostMapping(path = "/addTarjeta", consumes = "application/json")
	public void insertTarjeta(@RequestBody Tarjeta tarjeta) {

		// System.out.println(book);
		tarjetaRepository.save(tarjeta);
	}
	// ---------------------- crud: UPADATE (by Id) -------------------------------
		// ----------------------------------------------------------------------------
		@PutMapping("/updateTarjeta/{id}")
		public void upadatetarjeta(@RequestBody Tarjeta tarjeta, @PathVariable int id) {

			Optional<Tarjeta> tarjetaFound = tarjetaRepository.findById(id);

			if (tarjetaFound.isPresent()) {

				if (tarjeta.getNumero() == (tarjetaFound.get().getNumero()))
					tarjetaFound.get().setNumero(tarjeta.getNumero());

				if (!tarjeta.getFechaDecaducidad().equals(tarjetaFound.get().getFechaDecaducidad()))
					tarjetaFound.get().setFechaDecaducidad(tarjeta.getFechaDecaducidad());

				if (tarjeta.getBanco().equals(tarjetaFound.get().getBanco()))
					tarjetaFound.get().setBanco(tarjeta.getBanco());

				if (!tarjeta.getDebitoOcredito().equals(tarjetaFound.get().getDebitoOcredito()))
					tarjetaFound.get().setDebitoOcredito(tarjeta.getDebitoOcredito());

				if (tarjeta.getCVV() !=tarjetaFound.get().getCVV())
					tarjetaFound.get().setCVV(tarjeta.getCVV());

				if (!tarjeta.getMasterOvisa().equals(tarjetaFound.get().getMasterOvisa()))
					tarjetaFound.get().setMasterOvisa(tarjeta.getMasterOvisa());

				tarjetaRepository.save(tarjetaFound.get());
			}
		}
}
		
		
		
	   
		
		
	
	