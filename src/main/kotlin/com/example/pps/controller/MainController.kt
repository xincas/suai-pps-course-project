package com.example.pps.controller

import com.example.pps.model.User
import com.example.pps.model.UserRep
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class MainController {

    @Autowired
    private lateinit var userRep: UserRep

    @GetMapping("/")
    fun home() = "registration"

    @GetMapping("/registration")
    fun getRegistration() = "registration"

    @PostMapping("/registration")
    fun postUser(@RequestBody user: User): ResponseEntity<String> {
        if (userRep.existsByLogin(user.login)) {
            return ResponseEntity("Пользователь с таким логином уже существует!", HttpStatus.BAD_REQUEST)
        }

        if (userRep.existsByEmail(user.email)) {
            return ResponseEntity("Пользователь с такой почтой уже существует!", HttpStatus.BAD_REQUEST)
        }

        if (userRep.existsByPhone(user.phone)) {
            return ResponseEntity("Пользователь с таким телефоном уже существует!", HttpStatus.BAD_REQUEST)
        }

        userRep.save(
            User(
                id = 0,
                login = user.login,
                password = user.password,
                birth = user.birth,
                phone = user.phone,
                email = user.email,
                name = user.name,
                secondName = user.secondName,
                patronymic = user.patronymic,
                registerTime = user.registerTime
            )
        )

        return ResponseEntity("Пользователь успешно зарегестрирован", HttpStatus.CREATED)
    }

    @GetMapping("/rating")
    fun getUsers(model: Model): String {
        val users = userRep.findByOrderByRegisterTimeAsc()
        model.addAttribute("users", users)
        return "rating"
    }
}