package com.fullstackapplication.restaurantListing.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fullstackapplication.restaurantListing.DTO.ResturantDTO;
import com.fullstackapplication.restaurantListing.Entity.Resturant;
import com.fullstackapplication.restaurantListing.repo.ResturantRepo;

@Service
public class ResturantService {
	
	@Autowired
	private ResturantRepo resturantRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ResturantDTO> findAllResturants(){
		List<Resturant> resturants = resturantRepo.findAll(); // this ORM(english language) so we need to convert into select means datbase language we use Dialect so it will convert our ORM language to db languages. 
		//map it to list of DTO
		List<ResturantDTO> listOfResturantDtos = resturants.stream().map(resturant -> this.resturantToDTO(resturant)).collect(Collectors.toList());
		return listOfResturantDtos;
	}
	
	public ResturantDTO createResturant(ResturantDTO resturantDTO) {
		Resturant dtoToResturant = this.dtoToResturant(resturantDTO);
		Resturant saveResturant = this.resturantRepo.save(dtoToResturant);
		return this.resturantToDTO(saveResturant);
	}
	
	public ResponseEntity<ResturantDTO> getResturantById(Integer id) {
		Optional<Resturant> resturantById = this.resturantRepo.findById(id);
			if(resturantById.isPresent()) {
				return new ResponseEntity<ResturantDTO>(resturantToDTO(resturantById.get()), HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	public Resturant dtoToResturant(ResturantDTO resturantDTO) {
		Resturant resturant = this.modelMapper.map(resturantDTO, Resturant.class);
		
//		resturant.setId(resturantDTO.getId());
//		resturant.setName(resturantDTO.getName());
//		resturant.setAddress(resturantDTO.getAddress());
//		resturant.setCity(resturantDTO.getCity());
//		resturant.setRestrurantDescription(resturantDTO.getResturantDescription());

		return resturant;
	}
	
	public ResturantDTO resturantToDTO(Resturant resturant) {
		ResturantDTO resturantDto = this .modelMapper.map(resturant, ResturantDTO.class);
		
//		resturantDto.setId(resturant.getId());
//		resturantDto.setName(resturant.getName());
//		resturantDto.setAddress(resturant.getAddress());
//		resturantDto.setCity(resturant.getCity());
//		resturantDto.setRestrurantDescription(resturant.getResturantDescription());
		
		return resturantDto;
	}
	

}
