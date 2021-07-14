package cliente;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TarjetaController {
	
	
	@Autowired
	TarjetaRepository tarjetaRepository;
	
	//------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------
	@RequestMapping("/allTarjeta")
	public String getAllTarjeta(Model boxToView) {
		
		
		
		//System.out.println(clienteRepository.findAll());
		boxToView.addAttribute("tarjetaListfromControllerAndDB", tarjetaRepository.findAll() );
		//boxToView.addAttribute("message", "hi from NORWAY");
		//boxToView.addAttribute("smoker", true);
		//boxToView.addAttribute("taxesIVA", 21.00);
		
		
		
		
		return "tarjeta.html";
	}

	//------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------

@RequestMapping("/*")
public String notFound (Model model) {
	
	String pattern = "yyyy-MM-dd HH:mm:ssZ";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	model.addAttribute("serverTime", simpleDateFormat.format(new Date()));
	
	return "notFound.html";
}


//------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------

@RequestMapping("/deleteTarjeta")
public String removeTarjeta(int id, Model model) {
	
	//System.out.println("inside removeTarjeta" + id);
	Optional<Tarjeta> tarjetaFound = findOneTarjetaById(id);
	
	//System.out.println("find inside removeCliente" + clienteFound.get());
	
	if (tarjetaFound.isPresent()) {
		
		tarjetaRepository.deleteById(id);
		model.addAttribute("message", "deleted tarjeta confirmation");
		model.addAttribute( "tarjetaDeleted", tarjetaFound.get());
	}
	
	else {
		model.addAttribute("message", "deleted tarjeta error, maybe there is no id .... or network connection ..");
	}
	
	
	
	
	
	//System.out.println("finishing removeCliente" + id);
	return "redirect:/tarjeta/allTarjeta";
}




//--------------------------------------------------------------------------------
//------------------------- service to controller --------------------------------
//--------------------------------------------------------------------------------

	public Optional<Tarjeta> findOneTarjetaById(int id) {

		
		Optional<Tarjeta> tarjetaFound = tarjetaRepository.findById(id);
		
		return tarjetaFound;

	
	}
	
	@RequestMapping("/addTarjeta")
	public String inserTarjeta(Tarjeta tarjeta) {

		tarjetaRepository.save(tarjeta);

		return "redirect:/tarjeta/allTarjeta";
}

	@RequestMapping("/newTarjeta")
	public String newTarjeta() {

		return "newtarjeta.html";
	}

	@RequestMapping("/updateTarjeta")
	public String updateTarjeta(int id, Model model) {

		Optional<Tarjeta> tarjetaFound = findOneTarjetaById(id);

		if (tarjetaFound.isPresent()) {

			model.addAttribute("tarjetafromController", tarjetaFound.get());
			return "updatetarjeta";
		}

		else
			return "notfound.html";
	}
	
	
	

	@PostMapping("/replaceTarjeta/{idFromView}")
	public String replaceTarjeta(@PathVariable("idFromView") int id, Tarjeta tarjeta) {

		Optional<Tarjeta> tarjetaFound = findOneTarjetaById(id);

		if (tarjetaFound.isPresent()) {

			if (tarjeta.getNumero() != 0)
				tarjetaFound.get().setNumero(tarjeta.getNumero());
			//if (tarjeta.FechaDeCaducidad() != 00-00-00)
			//	tarjetaFound.get().setFechaDecaducidad(tarjeta.getFechaDecaducidad());
			if (tarjeta.getBanco() != null)
				tarjetaFound.get().setBanco(tarjeta.getBanco());
			if (tarjeta.getDebitoOcredito() != null)
				tarjetaFound.get().setDebitoOcredito(tarjeta.getDebitoOcredito());
			if (tarjeta.getCVV() != 0)
				tarjetaFound.get().setCVV(tarjeta.getCVV());
			if (tarjeta.getMasterOvisa() != null)
				tarjetaFound.get().setMasterOvisa(tarjeta.getMasterOvisa());

			tarjetaRepository.save(tarjetaFound.get());
			return "redirect:/tarjeta/allTarjeta";

		} else
			return "notfound.html";

	}
		@RequestMapping("/detailTarjeta")
		public String detailTarjeta(int id, Model model) {

			Optional<Tarjeta> tarjetaFound = findOneTarjetaById(id);

			if (tarjetaFound.isPresent()) {

				model.addAttribute("tarjetafromController", tarjetaFound.get());
				return "detailtarjeta";
			}

			else
				return "notfound.html";
	}


}





