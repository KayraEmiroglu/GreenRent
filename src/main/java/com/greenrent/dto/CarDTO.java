package com.greenrent.dto;



import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.greenrent.domain.Car;
import com.greenrent.domain.ImageFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
	
	private Long id;
	
	
	@Size(max=30,message = "Size is exceeded")
	@NotNull(message="Please provide car model")
	private String model;
	
	@NotNull(message="Please provide car door info")
	private Integer doors;
	
	@NotNull(message="Please provide car seat info")
	private Integer seats;
	
	@NotNull(message="Please provide car luggage info")
	private Integer luggage;
	
	@Size(max=30,message = "Size is exceeded")
	@NotNull(message="Please provide car transmission info")
	private String transmission;

	@NotNull(message="Please provide car airConditioning info")
	private Boolean airConditioning;
	
	@NotNull(message="Please provide car age info")
	private Integer age;
	
	@NotNull(message="Please provide car per hour price info")
	private Double pricePerHour;
	
	@Size(max=30,message = "Size is exceeded")
	@NotNull(message="Please provide car fuelType info")
	private String fuelType;
	
	private Boolean builtIn=false;
	
	//String tutma sebebimiz imageId'sini veriyoruz.
	private Set<String> image;
	
	//Veri tabanından constructor ile DTO çekebiliyorduk
	//o yüzden constructor ekliyoruz
	public CarDTO(Car car) {
		this.id=car.getId();
		this.model=car.getModel();
		this.doors=car.getDoors();
		this.seats=car.getSeats();
		this.luggage=car.getLuggage();
		this.transmission=car.getTransmission();
		this.airConditioning=car.getAirConditioning();
		this.age=car.getAge();
		this.pricePerHour=car.getPricePerHour();
		this.fuelType=car.getFuelType();
		this.builtIn=car.getBuiltIn();
		this.image=getImageId(car.getImage());
	}
	
	
	public Set<String> getImageId(Set<ImageFile> images){
		Set<String> imgStrSet= new HashSet<>();
		imgStrSet=images.stream().
				map(image->image.getId().toString()).collect(Collectors.toSet());
		return imgStrSet;
	}

}
