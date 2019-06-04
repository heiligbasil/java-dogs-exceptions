package com.lambdaschool.dogsinitial

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dogs")
class DogController {
    // localhost:8080/dogs/dogs
    val allDogs: ResponseEntity<*>
        @GetMapping(value = ["/dogs"])
        get() = ResponseEntity(DogsInitialApplication.getOurDogList().dogList, HttpStatus.OK)


    // localhost:8080/dogs/{id}
    @GetMapping(value = ["/{id}"])
    fun getDogDetail(@PathVariable id: Long): ResponseEntity<*> {
        val rtnDog = DogsInitialApplication.getOurDogList().findDog(CheckDog { d -> d.id == id })
        return ResponseEntity<Dog>(rtnDog, HttpStatus.OK)
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = ["/breeds/{breed}"])
    fun getDogBreeds(@PathVariable breed: String): ResponseEntity<*> {
//        val rtnDogs = DogsInitialApplication.getOurDogList().findDogs({ d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()) })
        val rtnDogs = DogsInitialApplication.getOurDogList().findDogs(CheckDog { d -> d.breed.toUpperCase() == breed.toUpperCase()})
        return ResponseEntity(rtnDogs, HttpStatus.OK)
    }


}