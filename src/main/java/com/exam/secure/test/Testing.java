package com.exam.secure.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class Testing {

    @Autowired
    private TestRepo testRepo;

    @GetMapping("/test")
    public String testing()
    {
        return "testing is DONE !";
    }


    @GetMapping("/staticobject")
    public UserModel staticObj()
    {
        UserModel userModel =new UserModel();
        userModel.setName("I am static Object");
        userModel.setMobile("981864415");
        userModel.setAddress("Address");
        userModel.setId(100);
        return userModel;
    }

    @GetMapping("/objtodb")
    public UserModel objtodb()
    {
        UserModel userModel =new UserModel();
        userModel.setName("I am static Object");
        userModel.setMobile("981864415");
        userModel.setAddress("Address");
        return  testRepo.save(userModel);
    }

    @GetMapping("/getTestList")
    public List<UserModel> getTestList()
    {
        return  testRepo.findAll();
    }

}
