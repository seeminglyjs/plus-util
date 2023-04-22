package com.source.plusutil.test;

import com.source.plusutil.test.dto.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plus")
public class ApiTestController {

    @GetMapping("/api/hello")
    public List<Client> test() {
        Client client1 = Client.builder()
                .id(1L)
                .name("test1")
                .email("test1@test1.com")
                .build();
        Client client2 = Client.builder()
                .id(2L)
                .name("test2")
                .email("test2@test1.com")
                .build();
        List<Client> list = new ArrayList<>();
        list.add(client1);
        list.add(client2);
        return list;
    }
}
