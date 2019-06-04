package com.lambdaschool.dogsinitial

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class DogsInitialApplication {

    companion object {
        private lateinit var ourDogList: DogList

        @JvmStatic
        fun main(args: Array<String>) {
            ourDogList = DogList()
            SpringApplication.run(DogsInitialApplication::class.java, *args)
        }

        fun getOurDogList() : DogList = ourDogList


    }
}