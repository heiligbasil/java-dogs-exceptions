package com.lambdaschool.dogsinitial.controller

import com.lambdaschool.dogsinitial.CheckDog
import com.lambdaschool.dogsinitial.DogsInitialApplication
import com.lambdaschool.dogsinitial.model.Dog
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping("/dogs")
class DogController
{
    // localhost:8080/dogs/dogs
    val allDogs: ResponseEntity<*>
        @GetMapping(value = ["/dogs"])
        get() = ResponseEntity(DogsInitialApplication.getOurDogList().dogList, HttpStatus.OK)


    // localhost:8080/dogs/{id}
    @GetMapping(value = ["/{id}"])
    fun getDogDetail(@PathVariable id: Long): ResponseEntity<*>
    {
        val rtnDog = DogsInitialApplication.getOurDogList().findDog(CheckDog { d -> d.id == id })
        return ResponseEntity<Dog>(rtnDog, HttpStatus.OK)
    }

    // localhost:8080/dogs/breeds/{breed}
    @GetMapping(value = ["/breeds/{breed}"])
    fun getDogBreeds(@PathVariable breed: String): ResponseEntity<*>
    {
//        val rtnDogs = DogsInitialApplication.getOurDogList().findDogs({ d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()) })
        val rtnDogs = DogsInitialApplication.getOurDogList().findDogs(CheckDog { d -> d.breed.toUpperCase() == breed.toUpperCase() })
        return ResponseEntity(rtnDogs, HttpStatus.OK)
    }

    //localhost:2019/dogs/dogtable
    @GetMapping(value = ["/dogtable"])
    fun displayDogTable(): ModelAndView
    {
        val dogList: MutableList<Dog> = DogsInitialApplication.getOurDogList().dogList
        dogList.sortBy { it.breed }

        val mav = ModelAndView()
        mav.viewName = "dogs"
        mav.addObject("dogList", dogList)

        return mav
    }

    //localhost:2019/dogs/suitabledogtable
    @GetMapping(value = ["/suitabledogtable"])
    fun displaySuitableDogTable(): ModelAndView
    {
        val dogList: List<Dog> = DogsInitialApplication.getOurDogList().dogList.filter { it.isApartmentSuitable }.sortedBy { a -> a.breed }

        val mav = ModelAndView()
        mav.viewName = "dogs"
        mav.addObject("dogList", dogList)

        return mav
    }
}